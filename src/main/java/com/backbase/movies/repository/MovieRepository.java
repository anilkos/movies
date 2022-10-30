package com.backbase.movies.repository;

import com.backbase.movies.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    Optional<Movie> findByTitle(String title);
}
