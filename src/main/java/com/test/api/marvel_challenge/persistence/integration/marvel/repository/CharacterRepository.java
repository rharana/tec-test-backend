package com.test.api.marvel_challenge.persistence.integration.marvel.repository;
import com.fasterxml.jackson.databind.JsonNode;
import com.test.api.marvel_challenge.dto.Pageable;
import com.test.api.marvel_challenge.persistence.integration.marvel.mapper.CharacterMapper;
import com.test.api.marvel_challenge.persistence.integration.marvel.MarvelAPIConfig;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.CharacterDTO;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.CharacterInfoDTO;
import com.test.api.marvel_challenge.service.IHttpClientService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Repository
public class CharacterRepository {

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;

    @Autowired
    private IHttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;

    private String characterPath;

    @PostConstruct
    private void setPath() {
        characterPath = basePath.concat("/").concat("characters");
    }

    public List<CharacterDTO> findAll(
            Pageable pageable,
            String name,
            int[] comics,
            int[] series)
    {
        Map<String, String> queryparams = getQueryParamsForFindAll(pageable,name,comics,series);
        JsonNode response = httpClientService.doGet(characterPath, queryparams, JsonNode.class);

        return CharacterMapper.toDTOList(response);
    }

    public CharacterInfoDTO findInfoById(Long id) {
        Map<String, String> queryparams = marvelAPIConfig.getAuthenticationQueryParams();
        String finalURL = characterPath.concat("/").concat(Long.toString(id));
        JsonNode response = httpClientService.doGet(finalURL, queryparams, JsonNode.class);
        return CharacterMapper.toInfoDTOList(response).getFirst();
    }


    private Map<String, String> getQueryParamsForFindAll(Pageable pageable, String name, int[] comics, int[] series) {
        Map<String, String> queryparams = marvelAPIConfig.getAuthenticationQueryParams();
        queryparams.put("offset", Long.toString(pageable.offset()));
        queryparams.put("limit", Long.toString(pageable.limit()));
        if(StringUtils.hasText(name)) {
            queryparams.put("name", name);
        }
        if(comics!=null) {
            String comicsAsString = joinIntArray(comics);
            queryparams.put("comics", comicsAsString);
        }

        if(series!=null) {
            String seriesAsString = joinIntArray(series);
            queryparams.put("series", seriesAsString);
        }
        return queryparams;
    }

    private String joinIntArray(int[] array) {
        List<String> stringArray =
                IntStream.of(array).boxed()
                        .map(Object::toString)
                        .toList();

        return String.join(",", stringArray);
    }


}