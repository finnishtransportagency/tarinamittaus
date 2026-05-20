# Instructions

## First time setup

Preferably start the constainers individually, first the database container

- `docker-compose up --build db`

and then the application container

- `docker-compose up --build tarina`

Or both application and db with a single command

- `docker-compose up --build`

If these commands result in an error it might be caused by an existinng tarinamittaus db containers. In that case, use `docker ps -a`
to check the existing tarinamittaus container names and remove them container with `docker rm -f <name_of_container>` and try again.

## After first time setup

When the application and database containers are already created, you can start them with commands below. The container
names might differ a little, so check the names with `docker ps -a` and use the correct names in the commands if 
there are any errors.

- `docker start tarinamittaus_db_1`
- `docker start tarinamittaus_tarina_1`.

Locally the application will be available at `http://localhost:8081/#/mittauslista`

Without docker `mvn clean install -Dmaven.test.skip=true` and `mvn spring-boot:run` (this is hardly ever used)

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
