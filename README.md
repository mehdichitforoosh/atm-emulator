# ATM Emulator

ATM is a part of our life activity, which helps us in day transactions and business. An automated teller machine (ATM) is a computerized telecommunications instrument that provides the clients of a financial institution with access to financial transactions in a public space without the need for a cashier, human clerk or bank teller.

At this time, the ATM provides the people good services especially the people can get money at any time. We need the ATM system because not all the bank branches are open all days of the week, and some of the customers may not in a situation, they can visit the bank every time, and they want to withdraw money or deposit money for emergency cases.

## How to run on Docker
1. Install [Docker Engine](https://docs.docker.com/engine/install/)
2. Install [Docker Compose](https://docs.docker.com/compose/install/)
3. Clone [Git URL](https://github.com/mehdichitforoosh/atm-emulator.git)
4. `cd atm-emulator`
5. `chmod +x cicd.sh`   
6. `$./cicd.sh`

## How to stop Docker Compose
1. `docker-compose down -v`

## How to run on local
1. Install Java 11
2. Install Maven > 3.3.1
3. Clone [Git URL](https://github.com/mehdichitforoosh/atm-emulator.git)
4. `cd atm-emulator`
5. `mvn spring-boot:run`

If you want to run server locally, change the **Postgresql** database url in `src/main/resources/application.yml` from `pgsql-db-01` to `localhost`.

Spring Boot automatically create the schema and initialize it with sample data.

You can see database shema in `src/main/resources/schema.sql` and initial data in `src/main/resources/data.sql`.

** You can see API documentation with [swagger-ui](http://localhost:8080/swagger-ui.html)

- [x] Session management mechanism (Jwt)
- [x] Validation
- [x] Standard list of operations with ATM
- [ ] Having unit and Integration tests
- [x] Having CI/CD configuration
- [x] Having swagger configured
- [x] Java doc comments
- [x] Clean code