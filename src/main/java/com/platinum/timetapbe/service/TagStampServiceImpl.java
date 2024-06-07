package com.platinum.timetapbe.service;

import com.platinum.timetapbe.documents.TagStamp;
import com.platinum.timetapbe.documents.User;
import com.platinum.timetapbe.dto.TagStampRequest;
import com.platinum.timetapbe.repository.TagStampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TagStampServiceImpl implements ITagStampService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    private TagStampRepository tagStampRepository;

    @Autowired
    public TagStampServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public TagStamp saveNewTagStamp(User user, TagStampRequest request) {
        TagStamp tagStampToSave = new TagStamp();
        tagStampToSave.setPositionLabel(request.getPositionLabel());
        tagStampToSave.setCoordinates(request.getCoordinates());
        tagStampToSave.setTimeCode(new Date());
        tagStampToSave.setUser(user);
        return this.tagStampRepository.save(tagStampToSave);
    }

    @Override
    public List<TagStamp> getTagHistory(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user.$id").is(user.getId()));
        return mongoTemplate.find(query, TagStamp.class);
    }
}
