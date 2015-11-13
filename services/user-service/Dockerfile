FROM java:8
RUN mkdir -p /usr/src/service/
COPY target/*.jar /usr/src/service/app.jar
WORKDIR /usr/src/service/
CMD ["java", "-jar", "app.jar"]
