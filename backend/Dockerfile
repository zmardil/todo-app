FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml ./
COPY src ./src
RUN mvn -DskipTests package

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /build/target/*.jar /app/todo-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/todo-backend.jar"]
