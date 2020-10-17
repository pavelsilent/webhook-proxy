FROM bellsoft/liberica-openjdk-alpine:11.0.7-10
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
