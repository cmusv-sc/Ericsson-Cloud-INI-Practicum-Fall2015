#similar-movie-service dockerfile
FROM java:8

MAINTAINER Yifan Li <thueeliyifan@gmail.com>

# containerize java applications
RUN mkdir -p /usr/src/service/
COPY target/*.jar /usr/src/service/app.jar
WORKDIR /usr/src/service/
CMD ["java", "-jar", "app.jar"]
