## Introduction
This repository represents API tests on CRUD operations for [restful-booker API](https://restful-booker.herokuapp.com/apidoc/index.html).

## Tech stack
* Rest Assured
* TestNG
* AssertJ
* Allure

## How to run the tests
### Prerequisites
1. [Docker](https://docs.docker.com/install/) installed
2. [Docker Compose](https://docs.docker.com/compose/install/) installed

### Run tests in Docker
1. Execute the following command to run tests:
```
docker compose up
```
2. Once "restful-booker-api-tests" container is finished, open test results report by navigating http://localhost:5050/allure-docker-service/projects/default/reports/latest/index.html