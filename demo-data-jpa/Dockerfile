FROM eclipse-temurin:21
VOLUME /tmp
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=dev","-jar","/app.jar"]