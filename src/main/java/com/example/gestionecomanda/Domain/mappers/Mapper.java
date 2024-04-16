package com.example.gestionecomanda.Domain.mappers;

public interface Mapper<A,B> {

    /**
     * Mappa l'oggetto A (Entità) nell'oggetto B (DTO)
     *
     * @param a A Entità
     * @return B DTO
     */
    B mapTo(A a);

    /**
     * Mappa l'oggetto B (DTO) nell'oggetto A (Entità)
     *
     * @param b B DTO
     * @return A Entità
     */
    A mapFrom(B b);

}
