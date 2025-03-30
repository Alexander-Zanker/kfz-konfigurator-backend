FROM gradle:jdk21-corretto AS builder
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .
RUN gradle --no-daemon clean build -x test

FROM openjdk:21-jdk-slim
WORKDIR /app
EXPOSE 8080
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
