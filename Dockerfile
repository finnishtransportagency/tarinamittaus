FROM node:12-alpine as BUILD_FRONTEND

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY ./src/main/app .
RUN npm install
RUN npm run build

FROM maven:3.6.0-jdk-11-slim AS BUILD_BACKEND

COPY ./src/main/java /data/src/main/java
COPY ./src/main/resources /data/src/main/resources
COPY pom.xml /data/
COPY ./lib/ojdbc6.jar /data/lib/ojdbc6.jar
COPY --from=BUILD_FRONTEND /usr/src/app/dist/ /data/src/main/webapp/

RUN cd /data && mvn clean install -Dmaven.test.skip=true

FROM tomcat:7-jdk8-openjdk

RUN mkdir ${CATALINA_HOME}/webapps/tarinamittaus

COPY --from=BUILD_BACKEND /data/target/TarinamittausUI.war ${CATALINA_HOME}/webapps/tarinamittaus/TarinamittausUI.war
RUN unzip ${CATALINA_HOME}/webapps/tarinamittaus/TarinamittausUI.war -d ${CATALINA_HOME}/webapps/tarinamittaus/
