FROM openjdk:22

WORKDIR /app

COPY target/sb-ecom.jar ./


EXPOSE 9090 5005
ENTRYPOINT ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "sb-ecom.jar"]
