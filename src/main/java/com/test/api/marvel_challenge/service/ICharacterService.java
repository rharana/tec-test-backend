package com.test.api.marvel_challenge.service;

import com.test.api.marvel_challenge.dto.Pageable;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.CharacterDTO;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.CharacterInfoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICharacterService {
    List<CharacterDTO> findAll(
            Pageable pageable,
            String name,
            int[] comics,
            int[] series);
    
    CharacterInfoDTO findInfoById(Long id);
}
