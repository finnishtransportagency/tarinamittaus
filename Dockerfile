# FROM node:12-alpine as BUILD_FRONTEND

# RUN mkdir -p /usr/src/app
# RUN mkdir -p /usr/src/webapp

# WORKDIR /usr/src/app

# COPY ./src/main/app .
# COPY ./src/main/webapp/* /usr/src/webapp/

# # RUN npm rebuild node-sass
# RUN npm install
# RUN npm run build

# RUN rm -rf ../webapp/build/*
# RUN cp -r build/* ../webapp/

FROM maven:3.6.0-jdk-11-slim AS BUILD_BACKEND

COPY ./src/main/java /data/src/main/
COPY ./src/main/resources /data/src/main/
COPY pom.xml /data/
COPY ./lib/ojdbc6.jar /data/lib/ojdbc6.jar
COPY ./src/main/webapp/WEB-INF /data/src/main/webapp/

RUN cd /data && mvn clean install -Dmaven.test.skip=true

FROM tomcat:7-jdk8-openjdk

RUN mkdir ${CATALINA_HOME}/webapps/tarinamittaus

COPY --from=BUILD_BACKEND /data/target/TarinamittausUI.war ${CATALINA_HOME}/webapps/tarinamittaus/TarinamittausUI.war
RUN unzip ${CATALINA_HOME}/webapps/tarinamittaus/TarinamittausUI.war -d ${CATALINA_HOME}/webapps/tarinamittaus/
