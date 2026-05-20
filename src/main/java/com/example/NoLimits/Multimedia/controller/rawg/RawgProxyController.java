package com.example.NoLimits.Multimedia.controller.rawg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/rawg")
public class RawgProxyController {

    @Value("${rawg.key}")
    private String rawgKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String RAWG_BASE = "https://api.rawg.io/api";

    @GetMapping("/**")
    public ResponseEntity<String> proxy(HttpServletRequest request) {
        String path = request.getRequestURI().replace("/api/rawg", "");
        String queryString = request.getQueryString();

        String url = RAWG_BASE + path + "?key=" + rawgKey
                   + (queryString != null ? "&" + queryString : "");

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return ResponseEntity.ok(response.getBody());
    }
}