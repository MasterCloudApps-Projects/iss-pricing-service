# iss-pricing-service

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=iss-pricing-service&metric=alert_status)](https://sonarcloud.io/dashboard?id=iss-pricing-service)

We decided to implement it in an extremely simplified version of an insurance sales system to test the following aspects of microservice development:

* Project creation and development
* Access of both relational and NoSQL databases
* Blocking and non-blocking operations implementation
* Microservice to microservice communication (synchronous and asynchronous)
* Service discovery
* Running background jobs

![Architecture](https://github.com/MasterCloudApps-Projects/iss-pricing-service/blob/master/images/iss-architecture.jpg?raw=true)

The iss-pricing-service microservice is responsible to calculated a price for the selected insurance product.

For each product, a tariff was defined. The tariff was a set of rules on the basis of which the price was calculated. MVEL language was used to define these rules. During the policy purchase process, the iss-policy-service connected with this service to calculate a price. The price was calculated based on the user’s answers to defined questions.

This is a spring boot project that exposes /api/v1/calculate endpoint and data can be persisted in postgresql database.

In the Integration Tests, the Testcontainers Java library is used for starting postgresql in a docker container.

## How to run Application

Running application from command line using Docker, this is the cleanest way.
In order for this approach to work, of course, you need to have Docker installed in your local environment.

* From the root directory you can run the following command:<br/>
    ```docker-compose -f docker/docker-compose.yml up --build```
* Application will be running on: http://localhost:8080/api/v1/calculate
* To stop it you can open other terminal in the same directory, and then run the following command:<br/>
    ```docker-compose -f docker/docker-compose.yml down```

## How to run the Unit Test

```mvn -B clean verify```

## How to run the Integration Test

#### Option 1:
* Import the testcontainers-demo application as a Maven project to your IDE.
* Run maven clean verify with profile 'integration-test' to build the project and start running the IT tests.<br/>
    ```mvn -B clean verify -Pintegration-test```

#### Option 2:
* Import the testcontainers-demo application as a Maven project to your IDE.
* Run maven clean install command to build the project.
* Then you can search for XXXIT.java or XXXE2ETestCase.java class and run it as a junit test.
