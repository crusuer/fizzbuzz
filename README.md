# FIZZ-BUZZ

This project is a [Spring Boot](http://projects.spring.io/spring-boot/) application.

## Running the application locally
### Terminal
To run this application in your terminal these are the requirements:
- [JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)

```shell
./gradlew clean build
./gradlew bootRun
```
### Docker
To run this application as a container these are the requirements:
- [JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Docker](https://www.docker.com/)

```shell
./gradlew clean build
docker run -it $(docker build -q .)
```

# REST API

The REST API to the app is described below:

## Create fizz-buzz sequence
### Request `POST /v1/fizzbuzz`
Params:
 - **int1**: integer
 - **int2**: integer
 - **limit**: integer
 - **str1**: string
 - **str2**: string


    curl --location --request POST 'http://localhost:8080/v1/fizzbuzz?int1=3&int2=4&limit=12&str1=ping&str2=pong' --header 'accept: application/json'

### Responses

#### Fizz-buzz sequence created

    HTTP/1.1 200 OK
    Date: Sun, 16 Feb 2025 19:30:49 GMT
    Content-Type: application/json
    Content-Length: 73
    Content: ["1","2","ping","pong","5","ping","7","pong","ping","10","11","pingpong"]

#### Invalid parameters sent

    HTTP/1.1 400 Bad Request
    Date: Sun, 16 Feb 2025 19:32:19 GMT
    Content-Type: application/json
    Content-Length: 45
    Content: int1, int2 and limit should be greater than 1

### Request `GET /v1/fizzbuzz/statistics`

    curl -i -H 'Accept: application/json' http://localhost:8080/v1/fizzbuzz/statistics

### Responses

#### No requests have been made

    HTTP/1.1 204 No Content
    Date: Sun, 16 Feb 2025 18:55:49 GMT
    Content-Type: application/json
    Content-Length: 0

#### Most frequent request
    HTTP/1.1 200 OK
    Date: Sun, 16 Feb 2025 19:48:03 GMT
    Content-Type: application/json
    Content-Length: 67
    Content: {"int1":3,"int2":4,"limit":12,"str1":"ping","str2":"pong","hits":2}
