FROM openjdk:11
COPY . /app
WORKDIR /app
VOLUME /tmp
ADD target/demoWithTests-0.0.1-SNAPSHOT.jar demoWithTests-0.0.1-SNAPSHOT.jar
EXPOSE 8087
ENTRYPOINT["java", "-jar", "demoWebProjForDocker-0.0.1-SNAPSHOT.jar"]

