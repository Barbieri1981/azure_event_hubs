# Send message using Java and the library Spring Cloud Stream Binder from Spring Boot Application

Expose a Restful API to send a message which is published to a `Event Hub` instance.

## Technology

- [Azure Portal](https://portal.azure.com/)
- [JDK8](https://www.oracle.com/java/technologies/downloads/) or later
- [Spring Cloud Stream](https://spring.io/blog/2019/04/02/event-driven-java-with-spring-cloud-stream-and-azure-event-hubs-guest-post)
- [Project Reactor](https://projectreactor.io/)
- Maven

## Run Locally

### Run the sample with Maven

In your terminal, run `mvn clean spring-boot:run`.

curl -X POST http://localhost:8080/messages/imperative?request=hello

curl -X POST http://localhost:8080/messages/reactive?request=hello
