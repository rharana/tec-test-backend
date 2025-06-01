package com.test.api.marvel_challenge.service.impl;
import com.test.api.marvel_challenge.dto.Pageable;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.CharacterDTO;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.CharacterInfoDTO;
import com.test.api.marvel_challenge.persistence.integration.marvel.repository.CharacterRepository;
import com.test.api.marvel_challenge.service.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements ICharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public List<CharacterDTO> findAll(
            Pageable pageable,
            String name,
            int[] comics,
            int[] series)
    {
        return characterRepository.findAll(pageable,name,comics,series);
    }

    @Override
    public CharacterInfoDTO findInfoById(Long id) {
        return characterRepository.findInfoById(id);
    }
}
