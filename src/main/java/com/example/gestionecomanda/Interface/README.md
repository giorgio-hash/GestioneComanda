### Command Line Consumer
Utilizzare il seguente comando per restare in ascolto sul topic "cucina.ordini"
```shell
docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092 --topic "cucina.ordini" --from-beginning
```