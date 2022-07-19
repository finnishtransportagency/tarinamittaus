# Instructions

Start application and db instances locally with docker-compose `docker-compose up --build`

Alternatively, `docker-compose up --build tarina` or `docker-compose up --build db` starts corresponding service from compose file.

Without docker `mvn clean install -Dmaven.test.skip=true` and `mvn spring-boot:run`

### Profiles
Application uses 3 profiles: local, docker and awsdev defined in pom.xml, with corresponding properties files in resources-folder.

- local: active default
- docker: environment file in ./config/.local_docker_env, which is passed into docker container
- awsdev: value is passed from EnvironmentVariables in CodeBuild

## Swagger
`http://localhost:8080/swagger-ui.html`

## Mapstruct
`https://mapstruct.org/`

## Spring data specification
`https://attacomsian.com/blog/spring-data-jpa-specifications`
