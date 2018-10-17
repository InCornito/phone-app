# Spring boot microservice using Java 10, Docker multi-stage, Spring Cloud, Eureka Server
This project was made as test task for Masmovil company.

PHONE-CATALOG returns a collection of phones, and their prices.
Phone should contain a reference to its image, the name, the description, and its price.

PHONE-ORDER receives and order that contains the customer information name, surname, and email, and the list of phones that the customer wants to buy. 
Calculates the total prices of the order. 
Logs the final order to the console.    
Should have endpoints to check(validate) and create and order. Extra Bonus Points: the second endpoint use the first endpoint to validate the order.    

## There are four microservices:
- **DISCOVERY** on port 8761
- **GATEWAY** on port 8080
- **PHONE-CATALOG** - responsible for managing phones(has dedicated database). on port 8070
- **PHONE-ORDER** - responsible for managing orders(has dedicated database). on port 8071

### EndPoints ###
Please use existed Postman collection: app.postman_collection.json 


### Requirements

- `docker`
- `docker-compose`

Ports 8080, 8070, 8071, 8761 are available on testing machine.
### How to do

- Build and run: `docker-compose up --build`
- Get:

```
$ curl -X GET localhost:8080/actuator/health
 Response:
 {
      status: "UP"
 }
```

- Clean all: `docker-compose down -t 0`


### Improvements:
create additional Spring Cloud Stream microservice based on RabbitMQ or Kafka in order to implement event driven 
system 
change default passwords on bd, queue
run java with optimized parameters
extract common classes to separate service-commons module

