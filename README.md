# Kafka MVP
This is a Proof-of-Concept for producing and consuming Kafka messages in a spring-boot app. It exposes an API through which the user produces/publishes a message (_transaction = text + amount of money + currency_) into a Kafka topic, and then the message is automatically fetched by a consumer (_being configured to connect to same broker_). The consumer polls/retrieves batches of at most 50 new messages, with a frequency of 10 seconds.

---

To run this application:
1) Install Docker: https://docs.docker.com/desktop/setup/install/windows-install/
2) Open a terminal CLI and execute command: `docker-compose up -d` that will create a Docker container with the Kafka Broker server and the Zookeeper server (_manages brokers, load balancing_).
3) The Docker container will create automatically also the Kafka topic named "_demo-topic_", but you can also create a new topic using this command: `bin/kafka-topics.sh --create --topic demo-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1`
4) Build the project by executing `mvn clean install`.
5) Start `DemoApplication`.
6) Send a message by calling the exposed API (_this is the request in cURL format_):
```
curl --location 'http://localhost:8080/produce' \
--header 'Content-Type: application/json' \
--data '{
"transactionMessage": "Payment through Kafka",
"amount": 100,
"currency": "EURO"
}'
```

![Kafka app runtime.png](Kafka%20app%20runtime.png)

---

# Kafka concepts

Kafka is a distributed platform that **streams bytes** using TCP (_3-way handshake_), asynchronously (_producer does not wait after response from consumer_). As **_analogy_**, Kafka is similar to a library, where authors/writers publish books (messages) which are organized on shelves (topics). Then readers retrieve books and they remember the last read book.

**Producers** send byte-encoded messages to a specific _**topic**_ within a Kafka Broker Server. The topic is a logical group of related messages, stored on multiple partitions to achieve scalability (_a partition is assigned to a topic_). A **ZooKeeper** manages orchestration and load balancing of Brokers.

Afterwards, **consumers** read and decode messages from the **topics** (_groups_). Messages within the same topic are stored into Kafka **brokers** (_copies for redundancy_) until a consumer reads them. Each consumer _pulls_ proactively the data, because he remembers the **ID/offset** of the last message that he read within each partition of a topic/group, and requests the next message by sending that ID to the Broker (_"send message with ID N+1 that follows after message with offset N"_). This allows the consumers to re-fetch older messages, because messages are not deleted after consumption (_as in RabbitMQ_). But since multiple consumers can be connected to same topic, they consume messages using the principle of _first-come first-served_.

<img alt="Kafka cluster sample.png" height="550" src="Kafka%20cluster%20sample.png"/>

A Kafka cluster usually consists of 3+ Kafka brokers. Why? Best practice is to have 3 copies of your data. In case of failure, you still have 2 brokers replicating data. Each broker is up-to-date with the data within the other brokers.

![Kafka example.png](Kafka%20example.png)

> _This [website](https://softwaremill.com/kafka-visualisation/) illustrates the live interaction between Kafka brokers, messages, producers, and consumers. But we cannot simulate topics._

![Kafka serialization and deserialization.png](Kafka%20serialization%20and%20deserialization.png)

But the message is not verified nor validated by Kafka, so the Consumer might not be able to parse/use a wrong message (_nor to report about a wrong message, because of the asynchronous communication_).

To fix this limitation, can be used a Schema Registry (_separate server_), in order to validate the published message, and to prevent wrong messages to be published. The registry stores the definition of a schema, written in Avro or Protobuf or JSON. When we execute `mvn clean install` to build the Maven project, the schema is translated into a Java class, that can be used within business-logic. For example, the `TransactionEvent.avsc` is converted into `TransactionEvent.java`.

![Kafka schema registry.png](Kafka%20schema%20registry.png)

### Kafka vs RabbitMQ:
* Kafka works as a Queue but in addition it organizes the messages on topics, and assigns an ID to each message, such that a consumer can request again older messages to support the retry/replay mechanisms.
* Kafka is pull-based, to allow scalability (_adding multiple consumers to a topic, without affecting the performance_). Each consumer can use different polling/fetching frequency (_a consumer polls in real time, another consumes messages in batches_). But RabbitMQ is push-based, because the exchange sends the message to a subscribed consumer.
* Kafka does not have a default management dashboard, as [RabbitMQ does](https://www.rabbitmq.com/docs/management#management-ui-screenshot-with-basic-authentication), but there are external tools like [Confluent](https://www.confluent.io/product/confluent-platform/gui-driven-management-and-monitoring/) and [AKHQ](https://akhq.io/) for monitoring the Kafka cluster.

### Use-cases and advantages:
* processing and storing millions of messages per second, and ability to replay historical data, to generate statistics or to re-run algorithms => **Big-Data applications** or **Logging**
* **Mozilla** => collect logs related to health, performance and usage (_and to broadcast them to other services that analyze them further_).
* **LinkedIn created Kafka for their newsfeed** => distribute/stream newly published posts to all applications. _As in Kafka logo, the producer (central circle) publishes/distributes/streams the messages, and consumers (smaller circles) read them._

### Resources:
* https://engineering.linkedin.com/teams/data/data-infrastructure/streams/kafka
* https://developers.redhat.com/articles/2022/06/02/how-create-kafka-consumers-and-producers-java#the_kafka_consumer
* https://medium.com/ing-tech-romania/implementing-a-basic-kafka-producer-and-consumer-using-spring-boot-spring-kafka-and-avro-schema-2b6d06e6c4cf
* https://codingharbour.com/apache-kafka/why-use-avro-data-format-with-apache-kafka/
* https://double.cloud/blog/posts/2023/03/the-many-use-cases-of-apache-kafka/
* https://double.cloud/blog/posts/2022/09/what-is-apache-kafka/
* https://stackoverflow.com/a/51829144/15285050
