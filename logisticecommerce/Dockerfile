FROM maven:3.6-jdk-8-alpine AS builder
WORKDIR /common
COPY ../voecommerce/pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn clean install -DskipTests
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B package -DskipTests
FROM openjdk:8-jre-alpine
COPY --from=builder /app/target/logisticecommerce-0.0.1-SNAPSHOT.jar /
EXPOSE 7001
CMD ["java", "-jar","/logisticecommerce-0.0.1-SNAPSHOT.jar"]