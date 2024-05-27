package com.platinum.timetapbe.repository;

import com.platinum.timetapbe.documents.TagStamp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagStampRepository extends MongoRepository<TagStamp, String> {

}
