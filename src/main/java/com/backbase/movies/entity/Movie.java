package com.backbase.movies.entity;


import com.backbase.movies.model.db.Award;
import com.backbase.movies.model.db.Rate;
import com.backbase.movies.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;


@Document(collection = "movies")
@Builder
@Data
public class Movie {
    @Id
    private String id;
    private String title;
    private String additionalInfo;
    private String year;
    private BigDecimal boxOffice;
    private Award award;
    private List<Rate> rates;

    @JsonIgnore
    public double getAverageRate() {
        return rates.stream()
                .mapToDouble(Rate::getUserRate)
                .average()
                .orElse(Constants.EMPTY);
    }

    @JsonIgnore
    public boolean isMovieWonBestPicture() {
        return award.isWon();
    }
}
