FROM maven:3.6.2-jdk-14 as builder

WORKDIR /app

COPY . /app

RUN mvn clean install

FROM openjdk:14-jdk

WORKDIR /app
EXPOSE 8080

COPY --from=builder /app/target/pos-estrategia-api.war /app/
CMD ["java","-jar","pos-estrategia-api.war"]
