package edu.ucmo;

import org.springframework.data.mongodb.repository.MongoRepository;
public interface NetflixRepository extends MongoRepository<Netflix, String>  {

}
