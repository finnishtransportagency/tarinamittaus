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

## Project structure

- buildspec.yml: build definition for AWS environment
- db: sql scripts for setting up the database
- docker-compose.yaml: used to start up the whole project locally
- Dockerfile, Dockerfile.aws Dockerfile_postgres: container definitions for local app, aws deployed app and local database
- src/main: frontend in `app` and backend in `java`
- src/main/resources: backend configuration

### Backend

- auth: JWT related code. User rights are unnecessary but copied over from tietokatalogi project
- Controller: rest endpoints
- Model: model objects (=database format) and data transfer objects (DTOs) (=api format)
- Service: business logic, calls to lower level operations
- Specification: Settings for search operations
- Util: dto <-> model mappings using mapstruct
- validator: Some validation code for calls, status unclear

### Frontend

- config/paths.js: copied over from tietokatalogi. Probably not needed, but originally there were problems with urls in deployment and this was one way to solve the problem
- public: static files
- src/api: rest api requests
- src/components: UI elements for form
- src/views: form views
- webpack.development.ts, webpack.production.ts: webpack configurations

Many (most?) configurations come from originally using create-react-app.
