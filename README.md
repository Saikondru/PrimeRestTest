# RESTful Prime Numbers

[![wercker status](https://app.wercker.com/status/b0a50c914a0d1575a3f63ef1869b32e7/s/master "wercker status")](https://app.wercker.com/project/byKey/b0a50c914a0d1575a3f63ef1869b32e7)

## Deployed to

https://prime-rest-java.herokuapp.com/

## Test with reports
```
./gradlew clean test jacocoTestReport
```

Code coverage will be available at  `build/reports/jacoco/test/html/index.html`

## IntegrationTests
```
./gradlew clean integrationTest
```

## Build
```
./gradlew build
```

Build is available at `build/libs/prime-rest-0.0.1-SNAPSHOT.jar`

## Run

```
java -jar build/libs/prime-rest-0.0.1-SNAPSHOT.jar
```

## Wercker commands

* Dev
```
wercker dev --publish 8080
```
Above command will run the application in a docker container setup by wercker

* Build
```
wercker build
```

## Content Negotiation

- PathExtension - *.xml and *.json
- Url parameter - mediaType=xml and mediaType=json
- Accept header
    - application/xml
    - application/json

