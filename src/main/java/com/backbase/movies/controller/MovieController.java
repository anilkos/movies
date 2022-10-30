package com.backbase.movies.controller;

import com.backbase.movies.dto.request.RateRequestDto;
import com.backbase.movies.dto.response.MovieResponseDto;
import com.backbase.movies.service.MovieService;
import com.backbase.movies.utils.Constants;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.REQUEST_MAPPING)
@EnableCaching
public class MovieController {

    private MovieService movieService;

    private MovieController (MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(Constants.RATE)
    @ApiOperation("Rate movie by title")
    public ResponseEntity rateMovie(@Valid @RequestBody final RateRequestDto rateRequestDto) {
        movieService.rateMovie(rateRequestDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(Constants.AWARD)
    @ApiOperation("Is movie won oscar ?")
    public ResponseEntity<Boolean> isMovieWonOscar(@RequestParam final String title) {
        return new ResponseEntity<>(movieService.isWonBestPicture(title), HttpStatus.OK);
    }

    @GetMapping(Constants.TOP_RATED)
    @ApiOperation("Get top rated movies ordered by boxOffice")
    public ResponseEntity<List<MovieResponseDto>> getTopRatedMovies() {
        return new ResponseEntity<>(movieService.getTopMovies(Constants.TOP_COUNT), HttpStatus.OK);
    }
}