FROM maven:3.9-amazoncorretto-21 AS build

WORKDIR /app

COPY mvnw .
COPY pom.xml .
COPY .mvn/wrapper/* ./.mvn/wrapper/

COPY . .

RUN ./mvnw package

EXPOSE 8080/tcp

CMD ["java", "-jar" ,"target/gTodo-0.0.1-SNAPSHOT.jar"]
