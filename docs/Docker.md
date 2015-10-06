# Building a Spring Boot app into a Docker container


Install Docker
==============

* For OS X and Windows, follow the instructions in the [Docker Toolbox](https://www.docker.com/toolbox) page.
* For Linux, follow the instructions [here](https://docs.docker.com/installation/ubuntulinux/).

Verify your Docker installation by running `docker ps`. No error should occur.

Register for a DockerHub account
================================

Create a Docker Hub account [here](https://hub.docker.com/) and login by running `docker login`

Build a Docker Image
====================

* Copy the Java 8 Dockerfile from the root directory of this repository to the root directory of your Maven project.
* From your project root directory, build your Maven project with `mvn install`. Make sure there is only one .jar file under the `target/` subdirectory.
* Build a Docker image with `docker build -t <user>/<image> .` Replace <user> with your Docker Hub username.
* Test that the image is runnable with `docker run -i -t <user>/<image>`
* Push your image to Dockerhub with `docker push <user>/<image>`

