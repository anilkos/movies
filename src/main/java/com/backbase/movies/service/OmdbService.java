package com.backbase.movies.service;

import com.backbase.movies.model.api.OmdbResponse;

public interface OmdbService {

    OmdbResponse getMovieDetails(String title);
}
