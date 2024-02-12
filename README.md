### Features

* Dockerized
* Input validation
* Centralized exception handler
* Logging (rms.log file in the project directory)
* DTO mapper
* Open API Documentation
* Auditing with JPA
* Log Visualization with Graylog
*

## Technology Stack:

* Open JDK 17
* Open JRE 17
* Spring Boot 3.2.2
* Gradle 8.5

## Quick Start
* Docker 4.27.1 (latest)

        gradlew build dockerRun
* Every thing will be ready with the randomly generated data and hit the URL:

        http://localhost:8080/home

### Required API Endpoints

`GET /api/v1/orders/by/date` - order list of the current day.\
`GET /api/v1/sales/total/amount` - total sale amount of the current day.\
`GET /api/v1/users` - all of the registered customer list.\
`GET /api/v1/orders/by/user/{userId}` - entire order list of a customer.\
`GET /api/v1/sales/max/sale-day` - max sale day of a certain time range.\


### Open API documentation:
There are some other API end points that was exposed to manage Users, Items, Sales, Order

* Swagger UI:

        http://localhost:8080/swagger-ui/index.html
* API docs:

        http://localhost:8080/v3/api-docs


### Postman API Collection
Import the below file into the Postman:

        ./rms-postman.json

### Log visualization

* Simply run the docker compose file

      docker-compose -f graylog-docker-compose.yml up
* hit the URL

      http://localhost:9000/

### AWS Deployment:

* Hit the URL deployed version should be appeared.

          ec2-15-206-187-22.ap-south-1.compute.amazonaws.com:8080/swagger-ui/index.html

### Docker Hub Repository

      docker pull imhasib/rms