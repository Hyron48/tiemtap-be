package com.platinum.timetapbe.repository;

import com.platinum.timetapbe.documents.Privilege;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends MongoRepository<Privilege, String> {

    Privilege findByName(String name);

}
