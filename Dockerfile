FROM maven:3.6.3-jdk-8 AS builder

WORKDIR /usr/src/app

COPY pom.xml .
COPY dao-api/pom.xml dao-api/pom.xml
COPY dao-orm-impl/pom.xml dao-orm-impl/pom.xml
COPY service/pom.xml service/pom.xml
COPY filestorage/pom.xml filestorage/pom.xml
COPY seed/pom.xml seed/pom.xml
COPY web/pom.xml web/pom.xml
COPY web/src/main/webapp/WEB-INF/web.xml web/src/main/webapp/WEB-INF/web.xml
RUN mvn package && mvn clean

COPY ./ .
RUN mvn package -DskipTests



FROM tomcat:9-jre8-alpine

WORKDIR ${CATALINA_HOME}
RUN rm -rf webapps/*
COPY config/tomcat/server.xml config/tomcat/context.xml conf/
COPY --from=builder /usr/src/app/web/target/marketplace.war webapps/ROOT.war

RUN addgroup www-data && \
    adduser -D -H -u 1000 -s /bin/bash www-data -G www-data && \
    chown -R www-data:www-data webapps temp

USER www-data
CMD ["catalina.sh", "run"]


#FROM tomcat:9.0-jre8

#WORKDIR  ${CATALINA_HOME}
#RUN rm -rf webapps/*
#COPY config/tomcat/server.xml conf/server.xml
#COPY config/tomcat/context.xml conf/context.xml
#COPY --from=builder /usr/src/app/web/target/marketplace.war webapps/ROOT.war

#RUN chown -R www-data:www-data webapps temp

#USER www-data
#CMD ["catalina.sh", "run"]