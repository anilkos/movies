package com.backbase.movies.service;

import com.backbase.movies.dto.request.RateRequestDto;
import com.backbase.movies.dto.response.MovieResponseDto;

import java.util.List;

public interface MovieService {
    void rateMovie(RateRequestDto rateRequestDto);

    List<MovieResponseDto> getTopMovies(int topCount);

    boolean isWonBestPicture(String title);

}
