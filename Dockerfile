FROM openjdk:12

VOLUME /tmp

ADD ./target/st-microservice-ftp-0.0.1-SNAPSHOT.jar st-microservice-ftp.jar

EXPOSE 8080

ENTRYPOINT java -jar /st-microservice-ftp.jar