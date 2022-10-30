package com.backbase.movies.service.impl;


import com.backbase.movies.exception.OmdbAPiCallException;
import com.backbase.movies.model.api.OmdbResponse;
import com.backbase.movies.service.OmdbService;
import com.backbase.movies.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OmdbServiceImpl implements OmdbService {
    private final RestTemplate restTemplate;

    @Override
    public OmdbResponse getMovieDetails(String title) {
        OmdbResponse response;
        try {
            String uriString = UriComponentsBuilder.fromHttpUrl(Constants.API_URL)
                    .queryParam("t", "{t}")
                    .queryParam("apikey", "{apikey}")
                    .encode()
                    .toUriString();
            Map<String, String> params = new HashMap<>();
            params.put("t", title);
            params.put("apikey", Constants.APIKEY);
            response = restTemplate.getForEntity(uriString, OmdbResponse.class, params).getBody();
        } catch (Exception exception) {
            throw new OmdbAPiCallException(exception.getMessage());
        }
        return response;
    }
}
