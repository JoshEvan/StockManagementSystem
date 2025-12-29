FROM maven:3.9.6-eclipse-temurin-21 AS builder 
WORKDIR /opt/app
COPY mvnw pom.xml ./
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean install
RUN ls -la ./target

FROM eclipse-temurin:21.0.8_9-jre-jammy AS final
WORKDIR /opt/app/runner
EXPOSE 8080
COPY --from=builder /opt/app/target/app.jar opt/app/runner/app.jar
COPY start.sh .
#ENTRYPOINT ["java", "-jar", "/opt/app/runner/app.jar"]
ENTRYPOINT ["sh", "start.sh"]