#FROM java:8
#VOLUME /tmp
#ADD target/docker-spring-boot.jar docker-spring-boot.jar
#EXPOSE 8090
#ENTRYPOINT ["java","-jar","docker-spring-boot.jar"]

FROM java:8
VOLUME /tmp
EXPOSE 8020
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/docker-spring-boot.jar /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/app.jar"]