FROM adoptopenjdk:8-jre-hotspot

EXPOSE 8080

ADD target/h2-hibernate-example.war h2-hibernate-example.war

ENTRYPOINT ["java","-jar","h2-hibernate-example.war"]