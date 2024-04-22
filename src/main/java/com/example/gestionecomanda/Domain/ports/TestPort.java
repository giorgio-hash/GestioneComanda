package com.example.gestionecomanda.Domain.ports;

import com.example.gestionecomanda.Domain.dto.OrdineDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* TODO: mentre le chiamate GET e POST sul topic SendOrderEvent utilizzano un oggetto ordine da sostiure con OrderEvent,
 *       gli altri due topic utilizzano una stringa --> sostituire la stringa con un oggetto notifica
 */

/**
 * REST Controller seguendo lo stile Interface Driven Controller Interfaccia di TEST
 * che permette di interagire direttamente con alcuni componenti del sistema tramite apposite API
 */
@RequestMapping("/test")
public interface TestPort {

    /**
     * Salva nel database l'oggetto ordine dato un ordineDTO
     *
     * @param ordineDTO DTO dell'entità ordine da salvare
     * @return entità risposta che contiene l'oggetto creato e una risposta HTTP associata
     */
    @PostMapping(path="/order")
    ResponseEntity<OrdineDTO> addOrdine(@RequestBody OrdineDTO ordineDTO);

    /**
     * Restituisci l'ordine corrispondente all'id dato in input
     *
     * @param id id dell'ordine riqchiesto
     * @return entità risposta che contiene l'oggetto richiesto e una risposta HTTP associata
     */
    @GetMapping(path="order/{id}")
    ResponseEntity<OrdineDTO> getOrdine(@PathVariable int id);

    /**
     * Restituisce una lista con tutti gli ordini relativi a una data comanda
     *
     * @param idComanda id della comanda di riferimento
     * @return entità risposta che contiene la lista richiesta e una risposta HTTP associata
     */
    @GetMapping(path = "orders/{idComanda}")
    ResponseEntity<List<OrdineDTO>> getAllOrdersByIdComanda(@PathVariable int idComanda);

    /**
     * Aggiornamento parziale dell'entità ordine, è possibile fornire solamente gli oggetti da aggiornare
     *
     * @param id id dell'ordine da aggiornare
     * @param ordineDTO oggetto DTO con le modifiche da effettuare
     * @return entità risposta che contiene l'oggetto aggiornato e una risposta HTTP associata
     */
    @PatchMapping(path="order/{id}")
    ResponseEntity<OrdineDTO> partialUpdateOrdine(@PathVariable int id, @RequestBody OrdineDTO ordineDTO);

    /**
     * Cancella l'ordine con il dato ID dal databse
     *
     * @param id id dell'ordine da eliminare
     * @return entità risposta che contiene la risposta HTTP associata
     */
    @DeleteMapping(path = "/order/{id}")
    ResponseEntity deleteOrder(@PathVariable("id") int id);

    /**
     * Espone una API di POST con la quale è possibile iniettare all'interno del broker oggetti al fine di test
     * Si testa il topic sendOrderEvent da gestione comanda verso gestione cucina
     *
     * @param ordineDTO contenuto dell'oggetto da iniettare
     * @return entità risposta che contiene l'oggetto creato e una risposta HTTP associata
     * @throws JsonProcessingException eccezione sollevata dalla serializzazione
     */
    @PostMapping(path = "/sendorderevent")
    ResponseEntity<OrdineDTO> sendOrderEvent(@RequestBody OrdineDTO ordineDTO) throws JsonProcessingException;

    /**
     * Espone una API di GET con la quale è possibile ottenere l'ultimo messaggio letto sul topic SendOrderEvent
     * Si testa il topic notifyPrepEvent da gestione cucina verso gestione comanda
     *
     * @return entità risposta che contiene l'oggetto richiesto e una risposta HTTP associata
     */
    @GetMapping(path = "/sendorderevent")
    ResponseEntity<String> getMessageFromTopicSendOrderEvent();

    /**
     * Espone una API di POST con la quale è possibile iniettare all'interno del broker oggetti al fine di test
     * Si testa il topic notifyOrderEvent da gestione cliente verso gestione comanda
     *
     * @param message contenuto dell'oggetto da iniettare
     * @return entità risposta che contiene l'oggetto creato e una risposta HTTP associata
     * @throws JsonProcessingException eccezione sollevata dalla serializzazione
     */
    @PostMapping(path = "/notifyorderevent")
    ResponseEntity<String> sendNotifyOrderEvent(@RequestBody String message) throws JsonProcessingException;

    /**
     * Espone una API di GET con la quale è possibile ottenere l'ultimo messaggio letto sul topic NotifyOrderEvent
     * Si testa il topic notifyPrepEvent da gestione cucina verso gestione comanda
     *
     * @return entità risposta che contiene l'oggetto richiesto e una risposta HTTP associata
     */
    @GetMapping(path = "/notifyorderevent")
    ResponseEntity<String> getMessageFromTopicNotifyOrderEvent();

    /**
     * Espone una API di POST con la quale è possibile iniettare all'interno del broker oggetti al fine di test
     * Si testa il topic notifyPrepEvent da gestione cucina verso gestione comanda
     *
     * @param message contenuto dell'oggetto da iniettare
     * @return entità risposta che contiene l'oggetto creato e una risposta HTTP associata
     * @throws JsonProcessingException eccezione sollevata dalla serializzazione
     */
    @PostMapping(path = "/notifyprepevent")
    ResponseEntity<String> sendNotifyPrepEvent(@RequestBody String message) throws JsonProcessingException;

    /**
     * Espone una API di GET con la quale è possibile ottenere l'ultimo messaggio letto sul topic NotifyPrepEvent
     * Si testa il topic notifyPrepEvent da gestione cucina verso gestione comanda
     *
     * @return entità risposta che contiene l'oggetto richiesto e una risposta HTTP associata
     */
    @GetMapping(path = "/notifyprepevent")
    ResponseEntity<String> getMessageFromTopicNotifyPrepEvent();


    /**
     * Espone una API di GET con la quale è possibile richiedere al database tutti i clienti
     * @return una lista di oggetti Cliente serializzati
     */
    /*
    @GetMapping(path = "/clienti")
    Iterable<ClienteEntity> getClienti();
    */
}
