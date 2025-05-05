FROM openjdk:22

WORKDIR /app

COPY target/sb-ecom.jar app.jar


EXPOSE 9090 5005
ENTRYPOINT ["java", "-jar","app.jar"]
