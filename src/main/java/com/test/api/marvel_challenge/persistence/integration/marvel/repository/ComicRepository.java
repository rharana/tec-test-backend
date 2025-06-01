package com.test.api.marvel_challenge.persistence.integration.marvel.repository;
import com.fasterxml.jackson.databind.JsonNode;
import com.test.api.marvel_challenge.dto.Pageable;
import com.test.api.marvel_challenge.persistence.integration.marvel.MarvelAPIConfig;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.CharacterDTO;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.CharacterInfoDTO;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.ComicDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Repository
public class ComicRepository {

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;

    @Value("${integration.marvel.base-path}")
    private String basePath;

    private String comicPath;

    @PostConstruct
    private void setPath() {
        comicPath = basePath.concat("/").concat("characters");
    }

    public List<ComicDTO> findAll(Pageable pageable, Long characterId)
    {
        Map<String, String> queryparams = getQueryParamsForFindAll(pageable, characterId);
        JsonNode response = HTTPClientService.doGet(comicPath, queryparams, JsonNode.class);
        return ComicMapper.toDTOList(response);
    }

    public ComicDTO findById(Long comicId)
    {
        Map<String, String> queryparams = marvelAPIConfig.getAuthenticationQueryParams();
        String finalURL = comicPath.concat("/").concat(Long.toString(comicId));
        JsonNode response = HTTPClientService.doGet(finalURL, queryparams);
        return CharacterMapper.toDTOList(response).get(0);
    }

    private Map<String, String> getQueryParamsForFindAll(Pageable pageable, Long characterId) {
        Map<String, String> queryparams = marvelAPIConfig.getAuthenticationQueryParams();
        queryparams.put("offset", Long.toString(pageable.offset()));
        queryparams.put("limit", Long.toString(pageable.limit()));
        if(characterId != null) {
            queryparams.put("characterId", characterId.toString());
        }
        return queryparams;
    }
}
