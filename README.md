# Instructions

Start application and db instances locally with docker-compose `docker-compose up --build`

Alternatively, `docker-compose up --build tarina` or `docker-compose up --build db` starts corresponding service from compose file.
If these commands result in an error it might be caused by an existinng tarinamittaus db container. In that case, use `docker ps -a`
to check the tarinamittaus db container name and remove the container with `docker rm -f <name_of_container>` and try again.

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
