Spring and Docker Example
=============

This is a sample project to illustrate how to build and deploy a Spring Boot application to Docker and lattice.

## Clone this repo:
git clone https://github.com/rahulkj/spring-docker.git

## Modify the pom.xml
Replace `<docker.image.prefix>rjain</docker.image.prefix>` with `<docker.image.prefix>your-docker-hub-username</docker.image.prefix>`

## Build the project
Execute `mvn package docker:build`

## Running the docker image locally
`docker run -p 8080:8080 -t your-docker-hub-username/spring-docker`

**NOTE: Don't forget to export the docker environment variables**

## Pushing the image to docker hub
`docker login`
`docker push your-docker-hub-username/spring-docker`
Access the url: http://<your-docker-conatiner-ip>:8080


## Deploying the image to lattice
`ltc create spring-docker your-docker-hub-username/spring-docker`
Access the url: http://spring-docker.192.168.11.11.xip.io


Enjoy!