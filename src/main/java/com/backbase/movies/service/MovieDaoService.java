package com.backbase.movies.service;

import com.backbase.movies.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDaoService {

    Movie createMovie(Movie movie);

    Movie updateMovie(Movie movie);

    Optional<Movie> getMovieByTitle(String title);

    long getRecordCount();

    List<Movie> getTopRatedMovies(int topCount);
}
