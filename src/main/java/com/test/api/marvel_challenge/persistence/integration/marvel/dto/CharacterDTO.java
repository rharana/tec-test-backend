package com.test.api.marvel_challenge.persistence.integration.marvel.dto;

public record CharacterDTO(
        Long id,
        String name,
        String description,
        String modified,
        String resourceURI)
{

}
