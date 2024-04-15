## Command Line Producer
Utilizzare il seguente comando per pubblicare sul topic specificato un messaggio
```shell
docker exec --interactive --tty broker kafka-console-producer --bootstrap-server broker:9092 --topic "sendOrderEvent"
```
```shell
docker exec --interactive --tty broker kafka-console-producer --bootstrap-server broker:9092 --topic "notifyPrepEvent"
```
```shell
docker exec --interactive --tty broker kafka-console-producer --bootstrap-server broker:9092 --topic "notifyOrderEvent"
```

## Kafka Web UI
[Kafdrop](https://github.com/obsidiandynamics/kafdrop) è un'interfaccia utente Web per visualizzare i topic di Kafka 
e sfogliare i gruppi dei consumers.
Lo strumento visualizza informazioni circa: brokers, topics, partitions, consumers, e consente di visualizzare i messaggi.

Apri un browser e vai all'indirizzo http://localhost:9000.
