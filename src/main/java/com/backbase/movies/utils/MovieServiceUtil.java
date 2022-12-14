package com.backbase.movies.utils;


import com.backbase.movies.dto.request.RateRequestDto;
import com.backbase.movies.dto.response.MovieResponseDto;
import com.backbase.movies.entity.Movie;
import com.backbase.movies.model.api.OmdbResponse;
import com.backbase.movies.model.db.Award;
import com.backbase.movies.model.db.Rate;
import com.backbase.movies.model.db.User;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class MovieServiceUtil {
    private MovieServiceUtil() {
    }

    public static Movie mapOmdbResponseToMovie(OmdbResponse omdbResponse) {
        Award award = Award.builder()
                .won(omdbResponse.getAwards().contains(Constants.WON))
                .category("")
                .build();
        return Movie.builder()
                .rates(new ArrayList<>())
                .year(omdbResponse.getYear())
                .title(omdbResponse.getTitle())
                .additionalInfo(omdbResponse.getAdditionalInfo())
                .boxOffice(convertBoxOffice(omdbResponse.getBoxOffice()))
                .award(award)
                .build();
    }

    public static Rate mapRateDtoToRate(RateRequestDto rateRequestDto) {
        return Rate.builder()
                .user(new User(rateRequestDto.getUserId(), rateRequestDto.getUserName()))
                .id(UUID.randomUUID().toString())
                .userRate(rateRequestDto.getUserRate())
                .build();
    }

    public static MovieResponseDto mapMovieToResponse(Movie movie) {
        return MovieResponseDto.builder()
                .additionalInfo(movie.getAdditionalInfo())
                .boxOffice(NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(movie.getBoxOffice().doubleValue()))
                .id(movie.getId())
                .rate(movie.getAverageRate())
                .title(movie.getTitle())
                .year(movie.getYear())
                .build();
    }

    private static BigDecimal convertBoxOffice(String boxOffice) {

        if (boxOffice == null || boxOffice.equals(Constants.NOT_APPLICABLE))
            return BigDecimal.ZERO;
        return BigDecimal.valueOf(Double.parseDouble(boxOffice.replace(",", "").replace("$", "")));

    }
}
