# README #

This README would normally document whatever steps are necessary to get your application up and running.

### How do I get set up locally? ###
Please use the following environment variables:
```
DISCOVERY_SERVICE_PORT=8761
```

 1) to build jar in terminal in project folder enter: gradle build
 2) to run project: java -jar jar-name.jar


### Health check ###
 Request:
 GET http://localhost:8080/actuator/health 

 Response:
 ```
 {
      status: "UP"
 }
 ```
 