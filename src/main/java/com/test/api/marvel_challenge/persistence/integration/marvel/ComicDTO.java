package com.test.api.marvel_challenge.persistence.integration.marvel;

public record ComicDTO(
        Long id,
        String title,
        String description,
        String modified,
        String resourceURI,
        ThumbnailDTO thumbnail) {
}
