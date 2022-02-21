FROM amazoncorretto:17
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ratepay.jar
ENTRYPOINT ["java","-jar","ratepay.jar"]