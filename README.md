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

**Producers** send byte-encoded messages to a specific topic, which is a logical group of related messages. Afterwards, **consumers** read and decode messages from the **topics**. Messages within the same topic are stored into Kafka **brokers** (_copies/redundancy_) until a consumer reads them. A consumer requests the next message, by sending to the Broker the **ID/offset** of the last fetched message within a topic/group.

![Kafka example.png](Kafka%20example.png)
> _This [website](https://softwaremill.com/kafka-visualisation/) illustrates the live interaction between Kafka brokers, topics (_groups_), messages, producers, and consumers._

But the message is not verified nor validated by Kafka, so the Consumer might not be able to decode it.

![Kafka serialization and deserialization.png](Kafka%20serialization%20and%20deserialization.png)

To fix this limitation, can be used a Schema Registry (_separate server_), in order to validate the published message, and to prevent wrong messages to be published. The registry stores the definition of a schema, written in Avro or Protobuf or JSON. When we execute `mvn clean install` to build the Maven project, the schema is translated into a Java class, that can be used within business-logic. For example, the `TransactionEvent.avsc` is converted into `TransactionEvent.java`.

![Kafka schema registry.png](Kafka%20schema%20registry.png)

Use-cases:
* **Mozilla** => collect logs related to health, performance and usage (_and analyze them further_).
* **LinkedIn newsfeed** => distribute/stream newly published posts to all applications. _As in Kafka logo, the producer (central circle) publishes/distributes/streams the messages, and consumers (smaller circles) read them._

Resources:
* https://engineering.linkedin.com/teams/data/data-infrastructure/streams/kafka
* https://developers.redhat.com/articles/2022/06/02/how-create-kafka-consumers-and-producers-java#the_kafka_consumer
* https://medium.com/ing-tech-romania/implementing-a-basic-kafka-producer-and-consumer-using-spring-boot-spring-kafka-and-avro-schema-2b6d06e6c4cf
* https://codingharbour.com/apache-kafka/why-use-avro-data-format-with-apache-kafka/