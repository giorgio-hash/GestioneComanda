# Gestione Comanda
Componente del sistema [ServeEasy](https://github.com/giorgio-hash/ServeEasy.git).

Il microservizio Gestione Comanda si occupa di implementare i seguenti casi d'uso del gruppo sistema:

UC-1 “Gestione Comanda”, dettagliato:
- UC-1.1 : gestione priorità ordine, ogni ordine è caratterizzato da una priorità
- UC-1.2 : gestione coda ordini, ogni ordine è inserito in una coda ordini
- UC-1.3 : assegnazione ordini comanda, ogni ordine deve essere associato ad una comanda
- UC-1.4 : assegnazione comanda cliente, ogni comanda deve essere associata ad un cliente

Nello specifico quindi GestioneComanda risolve gli use case del gruppo “sistema”, rappresenta il cuore del sistema ed incorpora la logica di backend fondamentale per la gestione regolarizzata degli ordini da cliente a cucina, attraverso politiche di schedulazione a priorità progettate ed implementate con un algoritmo ad-hoc.

La comunicazione con gli altri microservizi avviene tramite Message Broker come segue:
- Il microservizio [GestioneCliente](https://github.com/giorgio-hash/GestioneCliente) comunica verso GestioneComanda tramite il topic Kafka NotifyOrderEvent.
- Il microservizio [GestioneComanda](https://github.com/giorgio-hash/GestioneComanda) comunica verso GestioneCucina tramite il topic Kafka SendOrderEvent.
- Il microservizio [GestioneCucina](https://github.com/giorgio-hash/GestioneCucina) comunica verso GestioneComanda tramite il topic Kafka NotifyPrepEvent.

La comunicazione con il databse avviene tramite un adapter JPA

Il microservizio è stato implementato seguendo lo stile architetturale esagonale, seguendo lo schema port/adapter, 
per questo motivo viene strutturato in 3 parti:

- ### Interface
    Adattatori ponte tra il mondo esterno e il core del sistema, consentono al microservizio di comunicare con altre applicazioni, servizi o dispositivi esterni in modo         indipendente dall'implementazione interna del sistema stesso. Sono presenti i seguenti Interface Adapter:
    - EventControllers: SubCucina e SubCliente, permettono la ricezione di messaggi tramite message broker dagli altri microservizi.

- ### Domain
    Definisce gli oggetti, le entità e le operazioni che sono pertinenti al problema che il microservizio gestisce.

- ### Infrastructure:
    Adattatori ponte tra il core del sistema e l'infrastruttura esterna, gestendo le chiamate e le operazioni necessarie per accedere e utilizzare le risorse infrastrutturali.     Sono presenti i seguenti Infrastructure Adapters: 
    - Repository: JPADBAdapter per la comunicazione con il database;
    - MessageBroker: CucinaPubAdapter per l'invio di messaggi sul topic verso il microservizio della cucina.

## Start
Apri Docker Desktop, apri un terminale e vai alla root directory del progetto e digita:
```shell
docker compose up
```
Manda in run il microservizio usando qualsiasi IDE oppure tramite Maven Wrapper con la seguente istruzione:
```shell
./mvnw clean install
./mvnw spring-boot:run
```

## User Interface

### Kafdrop
[Kafdrop](https://github.com/obsidiandynamics/kafdrop) è un'interfaccia utente Web per visualizzare i topic di Kafka
e sfogliare i gruppi dei consumers.
Lo strumento visualizza informazioni circa: brokers, topics, partitions, consumers, e consente di visualizzare i messaggi.

Apri un browser e vai all'indirizzo http://localhost:9000.

### phpMyAdmin
[phpMyAdmin](https://www.phpmyadmin.net/) è un'applicazione web che consente di amministrare un database MariaDB tramite un qualsiasi browser.

Apri un browser e vai all'indirizzo http://localhost:3307.

## Test
Il microservizio GestioneComanda è sprovvisto di un componente HTTP Controller nella sua Interfaccia (che contiene solo EventController), viene quindi creato un controller di TEST per interagire direttamente con i componenti del servizio ai soli fini di test.
E' quindi possibile usufruire di varie API di test per gestire gli ordini oppure per iniettare dell'esterno messaggi verso
il topic del broker o per fare il percorso opposto e leggere gli ultimi messaggi del topic.

via [Postman](https://web.postman.co//) tramite l'API all'indirizzo http://localhost:8080/...

- ### API di Test
    Documentazione completa API : https://documenter.getpostman.com/view/32004409/2sA3JDhkaG

- ### Test dei topic Kafka:
    - ### Command Line Producer
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
    
    - ### Command Line Consumer
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

## Analisi Statica
### Checkstyle
In questo progetto è integrato un tool per l'analisi statica:
[Apache Maven Checkstyle Plugin](https://maven.apache.org/plugins/maven-checkstyle-plugin/index.html)
Per generare il sito del progetto e i report eseguire:
```shell
 ./mvnw site
 ```
I file di report si troveranno in ```target/site```, in particolare il file di interesse è
```checkstyle.html``` che è possibile aprire tramite un qualsiasi browser.

Per poter personalizzare le regole è possibile modificare il file ```checkstyle.xml``` e seguire le indicazioni
dei commenti in apertura.

Il report è disponibile grazie a Github pages della repository ServeEasy al seguente link: [checkstyle.html](https://giorgio-hash.github.io/ServeEasy/Report/GestioneComanda/site/checkstyle.html)

### Script Python
Inoltre è presente uno script python per generare i file csv e i grafici associati ai report, per poterlo avviare
è necessario avere python installato sulla propria macchina ed eseguire il seguente comando
per installare le librerie necessarie:
```shell
 pip install -r python/requirements.txt
 ```
Succesivamente eseguire il seguente per poter avviare lo script:
```shell
 python main.py
 ```
I file csv e le immagini png verranno salvati in ```target/output```.
