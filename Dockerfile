#
# Build stage
#
FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY . /app/
RUN ./gradlew clean build -x test

#
# Package stage
#
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]