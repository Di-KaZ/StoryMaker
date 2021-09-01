FROM maven:3.6.0-jdk-11-slim AS build-stage

WORKDIR /app

COPY  pom.xml ./

COPY src/ ./src

RUN mvn -f pom.xml clean package -Pdocker -DprofileIdEnabled=true-DskipTests

FROM adoptopenjdk/openjdk11 AS production-stage

COPY --from=build-stage /app/target/story-0.0.1-SNAPSHOT.jar /app/storyAPI.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=docker", "/app/storyAPI.jar"]