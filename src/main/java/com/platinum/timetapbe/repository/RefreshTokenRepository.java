package com.platinum.timetapbe.repository;

import com.platinum.timetapbe.documents.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUserId(String aLong);

    Optional<RefreshToken> findByRefreshToken(String token);
}