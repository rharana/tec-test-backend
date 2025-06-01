package com.test.api.marvel_challenge.persistence.integration.marvel.repository;
import com.test.api.marvel_challenge.dto.Pageable;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.ComicDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComicRepository {

    public List<ComicDTO> findAll(Pageable pageable, Long characterId)
    {
        return null;
    }

    public ComicDTO findById(Long comicId)
    {
        return null;
    }
}
