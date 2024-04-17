## Command Line Consumer
Utilizzare il seguente comando per restare in ascolto sul topic specifico
```shell
docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092 --topic "sendOrderEvent" --from-beginning
```
```shell
docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092 --topic "notifyPrepEvent" --from-beginning
```
```shell
docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092 --topic "notifyOrderEvent" --from-beginning
```

## Kafka Web UI
[Kafdrop](https://github.com/obsidiandynamics/kafdrop) è un'interfaccia utente Web per visualizzare i topic di Kafka
e sfogliare i gruppi dei consumers.
Lo strumento visualizza informazioni circa: brokers, topics, partitions, consumers, e consente di visualizzare i messaggi.

Apri un browser e vai all'indirizzo http://localhost:9000.

## Test 
è possibile usufruire di varie API di test per iniettare dell'esterno messaggi verso 
il topic del broker o per fare il percorso opposto e leggere gli ultimi messaggi del topic.
via [Postman](https://web.postman.co//) tramite l'API all'indirizzo http://localhost:8080/... 
### topic sendOrderEvent
```http request
POST /test/sendorderevent HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 41

{
    "id" : 19,
    "idComanda" : 6
}
``` 
```http request
GET /test/sendorderevent HTTP/1.1
Host: localhost:8080
``` 
### topic notifyOrderEvent
```http request
POST /test/notifyorderevent HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 30

"messaggio inviato da postman"
``` 
```http request
GET /test/notifyorderevent HTTP/1.1
Host: localhost:8080
``` 
### topic notifypPepEvent
```http request
POST /test/notifyprepevent HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 30

"messaggio inviato da postman"
``` 
```http request
GET /test/notifyprepevent HTTP/1.1
Host: localhost:8080
``` 