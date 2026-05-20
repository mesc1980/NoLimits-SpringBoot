package com.example.NoLimits.Multimedia.controller.tmdb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/tmdb")
public class TmdbProxyController {

    @Value("${tmdb.token}")
    private String tmdbToken;

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String TMDB_BASE = "https://api.themoviedb.org/3";

    @GetMapping("/**")
    public ResponseEntity<String> proxy(HttpServletRequest request) {
        // Extrae todo lo que viene después de /api/tmdb
        String path = request.getRequestURI().replace("/api/tmdb", "");
        String queryString = request.getQueryString();

        // Arma la URL hacia TMDB agregando la key internamente
        String url = TMDB_BASE + path + "?api_key=" + tmdbToken
                   + "&language=es-ES"
                   + (queryString != null ? "&" + queryString : "");

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return ResponseEntity.ok(response.getBody());
    }
}