FROM openjdk:22
#ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY ./target/TestDocker-0.0.1-SNAPSHOT.jar   app.jar

EXPOSE 8080 5005

ADD entrypoint.sh  entrypoint.sh

CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]
#ENTRYPOINT ["java", "-jar","/app.jar"]