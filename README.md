# Kafka MVP
This is a Proof-of-Concept for producing and consuming Kafka messages in a spring-boot app. It exposes an API through which the user produces/publishes a message (_transaction = text + amount of money + currency_) into a Kafka topic, and then the message is automatically fetched by a consumer.

---

To run this application:
1) Install Kafka: https://kafka.apache.org/downloads
2) Start Kafka Broker server: `bin/kafka-server-start.sh config/server.properties`
3) Start Zookeeper server (_manages brokers, load balancing_): `bin/zookeeper-server-start.sh config/zookeeper.properties`
4) Create Kafka topic named "_demo-topic_": `bin/kafka-topics.sh --create --topic demo-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1`
5) Build the project by executing `mvn clean install`.
6) Start `DemoApplication`.
7) Call the exposed API (_this is the request in cURL format_):
```
curl --location 'http://localhost:8080/produce' \
--header 'Content-Type: application/json' \
--data '{
"transactionMessage": "Payment through Kafka",
"amount": 100,
"currency": "EURO"
}'
```
8) Stop Kafka: `bin/kafka-server-stop.sh` 
9) Stop Zookeeper: `bin/zookeeper-server-stop.sh`

![Kafka app runtime.png](Kafka%20app%20runtime.png)

---

# Kafka concepts

Kafka is a distributed platform that **streams bytes** using TCP, asynchronously.

**Producers** send byte-encoded messages to a specific topic, which is a logical group of related messages. Afterwards, **consumers** read and decode messages from the **topics** (_groups_). Messages within the same topic are stored into Kafka **brokers** (_copies/redundancy_) until a consumer reads them. Each consumer pull proactively the data, because he remembers the **ID/offset** of the last message that he read within a topic/group, and requests the next message by sending that ID to the Broker.

![Kafka example.png](Kafka%20example.png)

As analogy, Kafka is similar to a library, where writers publish books (messages) which are organized on shelves (topics). Then readers retrieve books and they remember the last read book.

> _This [website](https://softwaremill.com/kafka-visualisation/) illustrates the live interaction between Kafka brokers, topics (_groups_), messages, producers, and consumers._

But the message is not verified nor validated by Kafka, so the Consumer might not be able to decode it.

![Kafka serialization and deserialization.png](Kafka%20serialization%20and%20deserialization.png)

To fix this limitation, can be used a Schema Registry (_separate server_), in order to validate the published message, and to prevent wrong messages to be published. The registry stores the definition of a schema, written in Avro or Protobuf or JSON. When we execute `mvn clean install` to build the Maven project, the schema is translated into a Java class, that can be used within business-logic. For example, the `TransactionEvent.avsc` is converted into `TransactionEvent.java`.

![Kafka schema registry.png](Kafka%20schema%20registry.png)

### Kafka vs RabbitMQ:
* Kafka works as a Queue but in addition it organizes the messages on topics, and assigns an offset (ID) to each message, such that a consumer can request again older messages. So Kafka is durable and supports more complex message handling.

### Use-cases and advantages:
* processing and storing millions of messages per second, and ability to replay historical data, to generate statistics or to re-run algorithms => **Big-Data applications** or **Logging**
* **Mozilla** => collect logs related to health, performance and usage (_and analyze them further_).
* **LinkedIn newsfeed** => distribute/stream newly published posts to all applications. _As in Kafka logo, the producer (central circle) publishes/distributes/streams the messages, and consumers (smaller circles) read them._

### Resources:
* https://engineering.linkedin.com/teams/data/data-infrastructure/streams/kafka
* https://developers.redhat.com/articles/2022/06/02/how-create-kafka-consumers-and-producers-java#the_kafka_consumer
* https://medium.com/ing-tech-romania/implementing-a-basic-kafka-producer-and-consumer-using-spring-boot-spring-kafka-and-avro-schema-2b6d06e6c4cf
* https://codingharbour.com/apache-kafka/why-use-avro-data-format-with-apache-kafka/
