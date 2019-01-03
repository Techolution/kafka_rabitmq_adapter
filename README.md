The purpose of this microservice is to allow messages to be produced to Kafka and forwarded to RabbitMQ.  There is a
docker-compose file that will start a zookeeper server, kafka broker, and a rabbitmq exchange.

To run this, you must have maven and docker/docker-compose setup.

1.  Execute a docker-compose up command from the root directory.  This will start up the rabbitMq, kafak, and
zookeeper servers.
2.  cd into the adapter folder
3.  mvn clean install
4.  mvn spring-boot:run

The microservice application is now started and connected to RabbitMq and Kafka.  To test it, you can send messages
using the Kafka Producer CLI.  You need to have the kafka binaries downloaded.  After they are downloaded, cd into the
kafka root directory.  Execute the following command:

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test

You can then send messages in the console.  Your messages should be printed in the log twice.  Once when it is picked up
by Kafka and another time when it is picked up by RabbitMQ.

