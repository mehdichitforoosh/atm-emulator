FROM dominant/dockerized-maven:3.8.1-jdk-11
LABEL author="Mehdi Chitforoosh"
COPY . /usr/src/project
WORKDIR /usr/src/project
CMD ["mvn", "spring-boot:run"]