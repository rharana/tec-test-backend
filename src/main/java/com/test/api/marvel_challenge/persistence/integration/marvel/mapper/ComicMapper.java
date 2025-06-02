package com.test.api.marvel_challenge.persistence.integration.marvel.mapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.ComicDTO;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.ThumbnailDTO;
import java.util.ArrayList;
import java.util.List;

public class ComicMapper {

    public static List<ComicDTO> toDTOList(JsonNode rootNode){
        ArrayNode resultsNode = getResults(rootNode);
        List<ComicDTO> comics = new ArrayList<>();
        resultsNode.elements().forEachRemaining(e-> comics.add(ComicMapper.toDTO(e)));
        return comics;
    }

    public static ComicDTO toDTO(JsonNode e) {
        if(e == null){
            throw new IllegalArgumentException("Node can not be null");
        }
        ThumbnailDTO thumbnailDTO = ThumbnailMapper.toDTO(e.get("thumbnail"));

        return new ComicDTO(
                e.get("id").asLong(),
                e.get("title").asText(),
                e.get("description").asText(),
                e.get("modified").asText(),
                e.get("resourceURI").asText(),
                thumbnailDTO
        );
    }

    private static ArrayNode getResults(JsonNode rootNode) {
        if(rootNode == null) {
            throw new IllegalArgumentException("Root node can not be null");
        }
        JsonNode dataNode = rootNode.get("data");
        return (ArrayNode) dataNode.get("results");
    }
}
