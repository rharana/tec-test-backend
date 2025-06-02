package com.test.api.marvel_challenge.persistence.integration.marvel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.ThumbnailDTO;

public class ThumbnailMapper {
    public static ThumbnailDTO toDTO(JsonNode thumbnailNode){
        if(thumbnailNode == null){
            throw new IllegalArgumentException("Thumbnail node cannot be null");
        }
        return new ThumbnailDTO(
                thumbnailNode.get("path").asText(),
                thumbnailNode.get("extension").asText()
        );
    }
}
