package com.bookmanagement.gestionlivres.service;

public interface DtoInterface <E,D> {
    /**
     * Convertir un Objet Dto en Entitie
     * @param dto
     * @return
     */
    E convertDtoToEntitie(D dto);

    /**
     * Convertir Une Entité en DTO
     * @param entitie
     * @return
     */
    D convertEntitieToDto(E entitie);
}
