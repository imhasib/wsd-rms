## Technology Stack:

* Open JDK 17
* Open JRE 17
* Spring Boot 3.2.2
* Gradle 8.5

## Quick Start
* Docker 4.27.1 (latest)

        gradlew dockerRun
* Every thing will be ready with the randomly generated data and hit the URL:

        http://localhost:8080/home

### Required API Endpoints

`GET /api/v1/orders/by/date` - order list of the current day.\
`GET /api/v1/sales/total/amount` - total sale amount of the current day.\
`GET /api/v1/users` - all of the registered customer list.\
`GET /api/v1/orders/by/user/{userId}` - entire order list of a customer.\
`GET /api/v1/sales/max/sale-day` - max sale day of a certain time range.\


### Open API documentation:
There are some other API end points to manage Users, Items, Sales, Order

* Swagger UI:

        http://localhost:8080/swagger-ui/index.html
* API docs:

        http://localhost:8080/v3/api-docs


### Features
* Dockerized
* Input validation
* Centralized exception handler
* Logging (rms.log file in the project directory)
* DTO mapper
* Open API Documentation
* Auditing with JPA