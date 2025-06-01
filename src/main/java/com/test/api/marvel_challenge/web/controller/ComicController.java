package com.test.api.marvel_challenge.web.controller;

import com.test.api.marvel_challenge.dto.Pageable;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.ComicDTO;
import com.test.api.marvel_challenge.service.IComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comics")
public class ComicController {

    @Autowired
    private IComicService comicService;
    @GetMapping
    public ResponseEntity<List<ComicDTO>> findAll(
            @RequestParam(required = false) Long characterId,
            @RequestParam(defaultValue = "10") Long offset,
            @RequestParam(defaultValue = "0") Long limit)
    {
        Pageable pageable = new Pageable(offset, limit);
        return ResponseEntity.ok(comicService.findAll(pageable, characterId));
    }

    @GetMapping("/{comicId}")
    public ResponseEntity<ComicDTO> findById(
            @PathVariable Long comicId)
    {
        return ResponseEntity.ok(comicService.findById(comicId));
    }
}
