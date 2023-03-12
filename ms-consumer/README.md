# Consume events using Java and the library Spring Cloud Stream Binder from Spring Boot Application

Expose a Restful API to consume events from a `Event Hub` instance.

## Technology

- [Azure Portal](https://portal.azure.com/)
- [JDK8](https://www.oracle.com/java/technologies/downloads/) or later
- [Spring Cloud Stream](https://spring.io/blog/2019/04/02/event-driven-java-with-spring-cloud-stream-and-azure-event-hubs-guest-post)
- Maven

### Configure the consumer microservice

Add the following properties to your *application.yaml* file.

``` yaml
spring:
  cloud:
    azure:
      eventhubs:
        namespace: ${AZURE_EVENTHUBS_NAMESPACE}
        processor:
          checkpoint-store:
            account-name: ${AZURE_STORAGE_CONTAINER_NAME}
            container-name: ${AZURE_STORAGE_ACCOUNT_NAME}
    stream:
      bindings:
        consume-in-0:
          destination: ${AZURE_EVENTHUB_NAME}
          group: ${AZURE_EVENTHUB_CONSUMER_GROUP}
      eventhubs:
        bindings:
          consume-in-0:
            consumer:
              checkpoint:
                mode: MANUAL
      function:
        definition: consume;
      poller:
        initial-delay: 0
        fixed-delay: 1000
```




| Field                                                                      | Description                                                  |
|----------------------------------------------------------------------------|--------------------------------------------------------------|
| `spring.cloud.azure.eventhubs.namespace`                                   | Event Hubs Namespace.                                        |
| `spring.cloud.azure.eventhubs.processor.checkpoint-store.account-name`     | Azure Blob Storage account name.                             |
| `spring.cloud.azure.eventhubs.processor.checkpoint-store.container-name`   | Event Hubs storage Container.                                |
| `spring.cloud.stream.bindings.consume-in-0.destination`                    | Event Hubs Instance.                                         |
| `spring.cloud.stream.bindings.consume-in-0.group`                          | Event Hubs consumer group.                                   |
| `spring.cloud.stream.eventhubs.bindings.consume-in-0.consumer.checkpoint`  | Checkpointing mode.                                          |
| `spring.cloud.stream.function.definition`                                  | Bean to consume events.                                      |
| `spring.cloud.stream.poller.fixed-delay`                                   | Poller in milliseconds. The default value is 1000 L.         |
| `spring.cloud.stream.poller.initial-delay`                                 | Initial delay for periodic triggers. The default value is 0. |


## Run Locally

### Run the sample with Maven

In the terminal, run

```shell
mvn clean spring-boot:run
```