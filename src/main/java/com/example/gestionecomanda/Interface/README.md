## Command Line Consumer
Utilizzare il seguente comando per restare in ascolto sul topic "cucina.ordini"
```shell
docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092 --topic "cucina.ordini" --from-beginning
```

## Kafka Web UI
[Kafdrop](https://github.com/obsidiandynamics/kafdrop) è un'interfaccia utente Web per visualizzare i topic di Kafka
e sfogliare i gruppi dei consumers.
Lo strumento visualizza informazioni circa: brokers, topics, partitions, consumers, e consente di visualizzare i messaggi.

Apri un browser e vai all'indirizzo http://localhost:9000.

## Test 
è possibile usufruire di una API di test per iniettare dell'esterno messaggi verso 
il topic del broker tramite l'API all'indirizzo http://localhost:8080/test/cucinaproducerconsumer/ 
via [Postman](https://web.postman.co//)
```http request
POST /visit HTTP/1.1
POST /test/cucinaproducerconsumer HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 42

{
    "value" : "messaggio da postman"
}
``` 
