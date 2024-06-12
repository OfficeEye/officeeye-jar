FROM maven:3.8.7-openjdk-18 as build
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ADD . /usr/src/app
RUN mvn clean
RUN mvn package

FROM eclipse-temurin:17-jdk

RUN apt-get update && apt-get install -y netcat

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/aplicacao-officeeye-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
ENTRYPOINT ["sh", "-c", "while ! nc -z db-mysql-container 3306; do sleep 1; done; java -jar app.jar"]
