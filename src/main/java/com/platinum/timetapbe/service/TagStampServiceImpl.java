package com.platinum.timetapbe.service;

import com.platinum.timetapbe.documents.TagStamp;
import com.platinum.timetapbe.dto.TagStampRequest;
import com.platinum.timetapbe.repository.TagStampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagStampServiceImpl implements ITagStampService {

    @Autowired
    private TagStampRepository tagStampRepository;

    @Override
    public TagStamp saveNewTagStamp(TagStampRequest request) {
        TagStamp tagStampToSave = new TagStamp();
        tagStampToSave.setPositionLabel(request.getPositionLabel());
        tagStampToSave.setCoordinates(request.getCoordinates());
        return this.tagStampRepository.save(tagStampToSave);
    }
}
