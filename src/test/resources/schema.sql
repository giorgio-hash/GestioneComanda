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
  CONSTRAINT `CONSTRAINT_1` CHECK (`stato` >= 0 and `stato` <= 1)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

