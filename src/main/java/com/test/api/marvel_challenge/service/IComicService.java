package com.test.api.marvel_challenge.service;

import com.test.api.marvel_challenge.dto.Pageable;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.ComicDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IComicService {
    List<ComicDTO> findAll(Pageable pageable, Long characterId);
    ComicDTO findById(Long comicId);
}
