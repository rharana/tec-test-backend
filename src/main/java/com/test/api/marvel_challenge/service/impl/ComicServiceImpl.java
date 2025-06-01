package com.test.api.marvel_challenge.service.impl;
import com.test.api.marvel_challenge.dto.Pageable;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.ComicDTO;
import com.test.api.marvel_challenge.persistence.integration.marvel.repository.ComicRepository;
import com.test.api.marvel_challenge.service.IComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicServiceImpl implements IComicService {

    @Autowired
    private ComicRepository comicRepository;

    @Override
    public List<ComicDTO> findAll(Pageable pageable, Long characterId) {
        return comicRepository.findAll(pageable, characterId);
    }

    @Override
    public ComicDTO findById(Long comicId) {
        return comicRepository.findById(comicId);
    }
}
