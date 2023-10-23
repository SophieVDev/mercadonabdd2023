FROM maven:4.0.0-eclipse-temurin-17
COPY . .
RUN mvn install
EXPOSE 8081
ENTRYPOINT ["java", "-jar", ".\target\mercadona-0.0.1-SNAPSHOT.war"]
