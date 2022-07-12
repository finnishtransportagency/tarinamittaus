package fi.tarina.tarinamittaus.Configs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.amazonaws.services.secretsmanager.model.InvalidParameterException;
import com.amazonaws.services.secretsmanager.model.InvalidRequestException;
import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Properties;
import org.springframework.core.env.Environment;


@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig {

    @Autowired
    private Environment env;

    public PersistenceJPAConfig() {
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(appDataSource());
        em.setPackagesToScan(new String[]{"fi.tarina.tarinamittaus.Model"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.dataSource.driver-class-name"));
        dataSource.setSchema(env.getProperty("spring.datasource.schema"));
        dataSource.setPassword(env.getProperty("spring.dataSource.password"));
        dataSource.setUsername(env.getProperty("spring.dataSource.username"));
        dataSource.setUrl(env.getProperty("spring.dataSource.url"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        String dialect = env.getProperty("spring.jpa.properties.hibernate.dialect");
        properties.setProperty("hibernate.dialect", dialect);

        return properties;
    }

    @Bean
    public DataSource appDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        String secretName = env.getProperty("spring.aws.secretsmanager.secretName");
        String endpoint = env.getProperty("spring.aws.secretsmanager.endpoint");
        String region = env.getProperty("spring.aws.secretsmanager.region");

        AwsClientBuilder.EndpointConfiguration config = new AwsClientBuilder.EndpointConfiguration(endpoint, region);
        AWSSecretsManagerClientBuilder clientBuilder = AWSSecretsManagerClientBuilder.standard();
        clientBuilder.setEndpointConfiguration(config);
        AWSSecretsManager client = clientBuilder.build();


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode secretsJson = null;

        ByteBuffer binarySecretData;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResponse = null;
        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);

        } catch (ResourceNotFoundException e) {
            System.out.println("log.error(The requested secret " + secretName + " was not found);");
        } catch (InvalidRequestException e) {
//            log.error("The request was invalid due to: " + e.getMessage());
        } catch (InvalidParameterException e) {
//            log.error("The request had invalid params: " + e.getMessage());
        }

        if (getSecretValueResponse == null) {
            return null;
        }

        // Decrypted secret using the associated KMS CMK
        // Depending on whether the secret was a string or binary, one of these fields will be populated
        String secret = getSecretValueResponse.getSecretString();
        if (secret == null) {
//            log.error("The Secret String returned is null");
            return null;
        }
        try {
            secretsJson = objectMapper.readTree(secret);
        } catch (IOException e) {
            System.out.println("log.error(Exception while retreiving secret values: " + e.getMessage());
        }


        System.out.println("Secrets json - " + secretsJson);
        String host = secretsJson.get("host").textValue();
        String port = secretsJson.get("port").textValue();
        String dbname = secretsJson.get("dbname").textValue();
        String username = secretsJson.get("username").textValue();
        String password = secretsJson.get("password").textValue();
        String schema = secretsJson.get("schema_tarina").textValue();
        dataSource.setUrl("jdbc:postgresql://" + host + ":" + port + "/" + dbname);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setSchema(schema);

        return dataSource;
    }

}
