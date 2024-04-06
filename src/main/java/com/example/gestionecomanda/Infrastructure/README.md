### Command Line Producer
Utilizzare il seguente comando per pubblicare sul topic "cucina.ordini" un messaggio
```shell
docker exec --interactive --tty broker kafka-console-producer --bootstrap-server broker:9092 --topic "cucina.ordini"
```