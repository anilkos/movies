package com.backbase.movies.utils;

import com.backbase.movies.entity.Movie;
import com.backbase.movies.model.db.Award;
import com.backbase.movies.model.file.RecordModel;
import com.backbase.movies.service.MovieDaoService;
import com.backbase.movies.service.impl.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class DatabaseUtil {
    private final MovieDaoService movieDaoService;
    private final FileServiceImpl fileService;

    @PostConstruct
    private void initDb() throws IOException, URISyntaxException {
        //load csv file only once
        if (movieDaoService.getRecordCount() <= 0) {
            Predicate<String> isMovie = s -> s.equals(Constants.BEST_PICTURE);
            List<RecordModel> records = fileService.readFile(Constants.INPUT_FILEPATH);
            records.stream()
                    .filter(i -> isMovie.test(i.getCategory()))
                    .forEach(this::saveRecord);
        }


    }

    private void saveRecord(RecordModel recordModel) {
        Award award = Award.builder()
                .category(recordModel.getCategory())
                .won(recordModel.isWon())
                .build();
        Movie movie = Movie.builder()
                .award(award)
                .title(recordModel.getNominee())
                .additionalInfo(recordModel.getAdditionalInfo())
                .year(recordModel.getYear())
                .rates(new ArrayList<>())
                .build();
        movieDaoService.createMovie(movie);
    }
}
