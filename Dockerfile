FROM dominant/dockerized-maven:latest
LABEL author="Mehdi Chitforoosh"
COPY . /usr/src/project
WORKDIR /usr/src/project
CMD ["mvn", "spring-boot:run"]