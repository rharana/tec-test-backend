package com.test.api.marvel_challenge.web.controller;
import com.test.api.marvel_challenge.dto.Pageable;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.CharacterDTO;
import com.test.api.marvel_challenge.persistence.integration.marvel.dto.CharacterInfoDTO;
import com.test.api.marvel_challenge.service.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private ICharacterService characterService;

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int[] comics,
            @RequestParam(required = false) int[] series,
            @RequestParam(defaultValue = "0") long offset,
            @RequestParam(defaultValue = "10") long limit)
    {
        Pageable pageable = new Pageable(offset, limit);
        return ResponseEntity.ok(characterService.findAll(pageable, name, comics, series));
    }
    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterInfoDTO> findInfoById(
            @PathVariable Long characterId)
    {
        return ResponseEntity.ok(characterService.findInfoById(characterId));
    }

}
