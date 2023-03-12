# Send events using Java and the library Spring Cloud Stream Binder from Spring Boot Application

Expose a Restful API to send events which is published to a `Event Hub` instance.

## Technology

- [Azure Portal](https://portal.azure.com/)
- [JDK8](https://www.oracle.com/java/technologies/downloads/) or later
- [Spring Cloud Stream](https://spring.io/blog/2019/04/02/event-driven-java-with-spring-cloud-stream-and-azure-event-hubs-guest-post)
- [Project Reactor](https://projectreactor.io/)
- Maven

### Configure the producer microservice

Add the following properties to your *application.yaml* file.

``` yaml
spring:
  cloud:
    azure:
      eventhubs:
        namespace: ${AZURE_EVENTHUBS_NAMESPACE}
    stream:
      bindings:
        supply-out-0:
          destination: ${AZURE_EVENTHUB_NAME}
      function:
        definition: supply;
```
| Field                                                     | Description             |
|-----------------------------------------------------------|-------------------------|
| `spring.cloud.azure.eventhubs.namespace`                  | Event Hubs Namespace.   |
| `spring.cloud.stream.bindings.producer-out-0.destination` | Event Hubs Instance.    |
| `spring.cloud.stream.function.definition`                 | Bean to produce events. |


## Run Locally

### Run the sample with Maven

In the terminal, run

```shell
mvn clean spring-boot:run
```

To send a event, in the terminal run   

```shell
curl --location --request POST 'http://localhost:8080/event?req=hello' \
--header 'Content-Type: text/plain' \
--data-raw '
'
```

To send a subscription, in the terminal run

```shell
curl --location --request POST 'http://localhost:8080/subscription' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "jorge.perez@gmail.com",
  "birthDate": "2010-10-11",
  "firstName": "Jorge",
  "gender": "M"
}'
```



