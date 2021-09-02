FROM maven:3.6.0-jdk-11-slim AS build-stage

WORKDIR /app

COPY  pom.xml ./

COPY src/ ./src/

RUN mv ./src/main/resources/prod.properties ./src/main/resources/application.properties

RUN mvn -f pom.xml clean package -DskipTests

FROM adoptopenjdk/openjdk11 AS production-stage

COPY --from=build-stage /app/target/story-0.0.1-SNAPSHOT.jar /app/storyAPI.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/storyAPI.jar"]