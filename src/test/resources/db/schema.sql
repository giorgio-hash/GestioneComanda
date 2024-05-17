CREATE SCHEMA IF NOT EXISTS serveeasy;
SET SCHEMA serveeasy;

CREATE table if not exists Cliente(
                                      ID varchar(10) PRIMARY KEY,
    t_o_a boolean NOT NULL
    );


CREATE TABLE if not exists Comanda (
                                       ID int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ID_cliente varchar(10) NOT NULL,
    codice_pagamento varchar(255) DEFAULT NULL,
    totale_scontrino float DEFAULT 0.0
    --FOREIGN KEY (ID_cliente) REFERENCES Cliente(ID)
    );

create table if not exists IngredientePrincipale(
                                                    ID varchar(20) primary key,
    nome varchar(20) not NULL
    );



CREATE TABLE if not exists Piatto(
                                     ID varchar(20) NOT NULL PRIMARY KEY,
    ID_ingr_princ varchar(20) NOT NULL,
    descrizione varchar(50),
    prezzo float(6) NOT NULL,
    t_preparazione int(10)
    --FOREIGN KEY (ID_ingr_princ) REFERENCES IngredientePrincipale(ID)
    );

CREATE TABLE IF NOT EXISTS Ordine(
                                     ID int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ID_comanda int(10) NOT NULL,
    ID_piatto varchar(20) NOT NULL,
    stato int(1) DEFAULT 0, -- 0=in preparazione, 1=completato
    t_ordinazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    urgenza_cliente int(2) DEFAULT 0 -- priorità del cliente: 1=massima, -1=minima
    --FOREIGN KEY (ID_comanda) REFERENCES Comanda(ID),
    --FOREIGN KEY (ID_piatto) REFERENCES Piatto(ID),
    --CHECK (stato >= 0 AND stato <=1 )
    );