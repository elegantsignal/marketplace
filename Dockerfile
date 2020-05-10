FROM tomcat:9.0-jdk8

RUN rm -rf ${CATALINA_HOME}/webapps/examples && \
	rm -rf ${CATALINA_HOME}/webapps/host-manager && \
	rm -rf ${CATALINA_HOME}/webapps/manager && \
	rm -rf ${CATALINA_HOME}/webapps/docs 

COPY config/tomcat/server.xml ${CATALINA_HOME}/conf/server.xml
COPY config/tomcat/context.xml ${CATALINA_HOME}/conf/context.xml
COPY web/target/marketplace.war ${CATALINA_HOME}/webapps/ROOT.war

RUN chown -R www-data:www-data ${CATALINA_HOME}/webapps

USER www-data

CMD ["catalina.sh", "run"]
