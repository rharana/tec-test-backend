package com.test.api.marvel_challenge.persistence.integration.marvel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.CharacterDTO;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.CharacterInfoDTO;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.ThumbnailDTO;

import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {

    public static List<CharacterDTO> toDTOList(JsonNode rootNode) {
        ArrayNode resultNode = getResults(rootNode);

        List<CharacterDTO> characters = new ArrayList<>();
        resultNode.elements().forEachRemaining(e->characters.add(CharacterMapper.toDTO(e)));
        return characters;
    }

    private static CharacterDTO toDTO(JsonNode characterNode) {
        if(characterNode == null) {
            throw new IllegalArgumentException("Node can not be null");
        }

        return new CharacterDTO(
                characterNode.get("id").asLong(),
                characterNode.get("name").asText(),
                characterNode.get("description").asText(),
                characterNode.get("modified").asText(),
                characterNode.get("resourceURI").asText()
        );
    }

    private static ArrayNode getResults(JsonNode rootNode) {
        if(rootNode == null) {
            throw new IllegalArgumentException("Root node can not be null");
        }
        JsonNode dataNode = rootNode.get("data");
        return (ArrayNode) dataNode.get("results");
    }

    public static List<CharacterInfoDTO> toInfoDTOList(JsonNode response) {
        ArrayNode resultNode = getResults(response);

        List<CharacterInfoDTO> characters = new ArrayList<>();
        resultNode.elements().forEachRemaining(e->characters.add(CharacterMapper.toInfoDTO(e)));
        return characters;

    }

    private static CharacterInfoDTO toInfoDTO(JsonNode infoNode) {
        if(infoNode == null) {
            throw new IllegalArgumentException("Node can not be null");
        }

        JsonNode thumbnailNode = infoNode.get("thumbnail");

        ThumbnailDTO thumbnailDTO = ThumbnailMapper.toDTO(thumbnailNode);

        String image = thumbnailDTO.path().concat(".").concat(thumbnailDTO.extension());

        return new CharacterInfoDTO(
                image,
                infoNode.get("description").asText()
        );
    }
}
