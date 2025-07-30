FROM maven:3.8-jdk-8 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn package -DskipTests

FROM openjdk:8-jre-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/target/*.jar /zast-output/app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]
