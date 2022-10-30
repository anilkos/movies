package com.backbase.movies.service.impl;


import com.backbase.movies.dto.request.RateRequestDto;
import com.backbase.movies.dto.response.MovieResponseDto;
import com.backbase.movies.entity.Movie;
import com.backbase.movies.exception.MovieObjectNotFoundException;
import com.backbase.movies.model.api.OmdbResponse;
import com.backbase.movies.service.MovieDaoService;
import com.backbase.movies.service.MovieService;
import com.backbase.movies.service.OmdbService;
import com.backbase.movies.utils.Constants;
import com.backbase.movies.utils.MovieServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.backbase.movies.utils.MovieServiceUtil.mapOmdbResponseToMovie;
import static com.backbase.movies.utils.MovieServiceUtil.mapRateDtoToRate;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieDaoService movieDaoService;
    private final OmdbService omdbService;

    @Override
    public void rateMovie(RateRequestDto rateRequestDto) {
        String title = rateRequestDto.getTitle();
        Movie movie = getMovie(title);
        movie.getRates().add(mapRateDtoToRate(rateRequestDto));
        movieDaoService.updateMovie(movie);
    }


    @Override
    public List<MovieResponseDto> getTopMovies(int topCount) {
        return movieDaoService.getTopRatedMovies(topCount).stream()
                .map(this::setBoxOffice)
                .sorted(Comparator.comparing(Movie::getBoxOffice).reversed())
                .map(MovieServiceUtil::mapMovieToResponse)
                .collect(Collectors.toList());
    }


    @Override
    public boolean isWonBestPicture(String title) {
        Movie movie = getMovie(title);
        return movie.isMovieWonBestPicture();
    }

    private Movie getMovie(String title) {
        Optional<Movie> movie = movieDaoService.getMovieByTitle(title);
        // If movie not found in DB, get it from API and save it.
        if (!movie.isPresent()) {
            movie = Optional.of(getMovieFromApi(title));
            movieDaoService.createMovie(movie.get());
        }
        return movie.get();
    }

    private Movie getMovieFromApi(String title) {
        //find movie and save it into the DB
        OmdbResponse omdbResponse = omdbService.getMovieDetails(title);
        if (omdbResponse.getResponse().equals(Constants.FALSE))
            throw new MovieObjectNotFoundException("Movie not found ! Title :" + title);
        return mapOmdbResponseToMovie(omdbResponse);
    }

    private Movie setBoxOffice(Movie movie) {

        movie.setBoxOffice(getMovieFromApi(movie.getTitle()).getBoxOffice());
        return movie;

    }

}
