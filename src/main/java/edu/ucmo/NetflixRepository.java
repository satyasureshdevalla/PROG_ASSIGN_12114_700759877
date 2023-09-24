package edu.ucmo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface NetflixRepository extends MongoRepository<Netflix, String>  {
    @Query("{ 'title' : ?0 }")
    Netflix findByTitle(String title) throws Exception;
}

