FROM openjdk:22
WORKDIR /app
COPY target/test.jar /app/test.jar
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "/app/test.jar"]
