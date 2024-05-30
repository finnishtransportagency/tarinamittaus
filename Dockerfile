FROM node:12-alpine as BUILD_FRONTEND

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY ./src/main/app .
RUN npm install
RUN npm run build

FROM maven:3.9-amazoncorretto-17 AS BUILD_BACKEND

COPY ./src/main/java /data/src/main/java
COPY ./src/main/resources /data/src/main/resources
COPY pom.xml /data/
COPY --from=BUILD_FRONTEND /usr/src/app/dist/ /data/src/main/resources/static/

RUN cd /data && mvn clean package -Dmaven.test.skip=true

# FROM tomcat:7.0.109-jdk8-adoptopenjdk-openj9
FROM amazoncorretto:17-alpine

# TODO: re-enable these
# RUN apt update && apt upgrade --quiet --yes
# RUN apt install unzip --quiet --yes

RUN mkdir tarinamittaus

COPY --from=BUILD_BACKEND /data/target/tarinamittaus-app.jar tarinamittaus/
ENTRYPOINT [ "java", "-jar", "tarinamittaus/tarinamittaus-app.jar" ]
