/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cliente` (
  `ID` varchar(10) NOT NULL,
  `t_o_a` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comanda` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `ID_cliente` varchar(10) NOT NULL,
  `codice_pagamento` varchar(255) DEFAULT NULL,
  `totale_scontrino` float DEFAULT 0,
  PRIMARY KEY (`ID`),
  KEY `ID_cliente` (`ID_cliente`),
  CONSTRAINT `Comanda_ibfk_1` FOREIGN KEY (`ID_cliente`) REFERENCES `Cliente` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IngredientePrincipale` (
  `ID` varchar(20) NOT NULL,
  `nome` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Piatto` (
`ID` varchar(20) NOT NULL,
`ID_ingr_princ` varchar(20) NOT NULL,
`descrizione` varchar(50) DEFAULT NULL,
`prezzo` float NOT NULL,
`t_preparazione` timestamp NULL DEFAULT current_timestamp(),
PRIMARY KEY (`ID`),
KEY `ID_ingr_princ` (`ID_ingr_princ`),
CONSTRAINT `Piatto_ibfk_1` FOREIGN KEY (`ID_ingr_princ`) REFERENCES `IngredientePrincipale` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
CREATE TABLE `Ordine` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `ID_comanda` int(10) NOT NULL,
  `ID_piatto` varchar(20) NOT NULL,
  `stato` int(1) DEFAULT 0,
  `t_ordinazione` timestamp NULL DEFAULT current_timestamp(),
  `urgenza_cliente` int(2) DEFAULT 0,
  PRIMARY KEY (`ID`,`ID_comanda`),
  KEY `ID_comanda` (`ID_comanda`),
  KEY `ID_piatto` (`ID_piatto`),
  CONSTRAINT `Ordine_ibfk_1` FOREIGN KEY (`ID_comanda`) REFERENCES `Comanda` (`ID`),
  CONSTRAINT `Ordine_ibfk_2` FOREIGN KEY (`ID_piatto`) REFERENCES `Piatto` (`ID`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`stato` >= 0 and `stato` <= 1)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

