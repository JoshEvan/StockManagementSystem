FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
COPY start.sh .
COPY .env .
RUN mkdir -p /app/web/generatedpdf
EXPOSE 8080
ENTRYPOINT ["sh","/app/start.sh"]
