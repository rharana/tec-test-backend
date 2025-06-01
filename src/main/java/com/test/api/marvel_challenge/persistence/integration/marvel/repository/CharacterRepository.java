package com.test.api.marvel_challenge.persistence.integration.marvel.repository;
import com.test.api.marvel_challenge.dto.Pageable;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.CharacterDTO;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.CharacterInfoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CharacterRepository {

    public List<CharacterDTO> findAll(
            Pageable pageable,
            String name,
            int[] comics,
            int[] series)
    {
        return null;
    }

    public CharacterInfoDTO findInfoById(Long id) {
        return null;
    }
}