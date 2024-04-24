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
è possibile usufruire di varie API di test per gestire gli ordini oppure per iniettare dell'esterno messaggi verso
il topic del broker o per fare il percorso opposto e leggere gli ultimi messaggi del topic.
via [Postman](https://web.postman.co//) tramite l'API all'indirizzo http://localhost:8080/...
### Orders manager
```http request
POST /test/order HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 90

{
    "idComanda":4,
    "idPiatto":"PIA770",
    "stato":0,
    "urgenzaCliente":1
}
```
```http request
GET /test/order/1 HTTP/1.1
Host: localhost:8080
```
```http request
PATCH /test/order/1 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 46

{
    "stato": 1,
    "urgenzaCliente": 1
}
```
```http request
DELETE /test/order/1 HTTP/1.1
Host: localhost:8080
```
```http request
GET /test/orders/4 HTTP/1.1
Host: localhost:8080
```
### Topic sendOrderEvent
```http request
POST /test/sendorderevent HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 150

{
    "id":1,
    "idComanda":7,
    "idPiatto":"SUH724",
    "stato":1,
    "urgenzaCliente":0,
    "tordinazione":"2024-04-25 12:04:57.127"
}
``` 
```http request
GET /test/sendorderevent HTTP/1.1
Host: localhost:8080
``` 
### Topic notifyOrderEvent
```http request
POST /test/notifyorderevent HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 39

{
    "id" : 2,
    "idComanda": 7
}
``` 
```http request
GET /test/notifyorderevent HTTP/1.1
Host: localhost:8080
``` 
### Topic notifyPrepEvent
```http request
POST /test/notifyprepevent HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 39

{
    "id" : 1,
    "idComanda": 4
}
``` 
```http request
GET /test/notifyprepevent HTTP/1.1
Host: localhost:8080
``` 
