package com.platinum.timetapbe.service;

import com.platinum.timetapbe.documents.TagStamp;
import com.platinum.timetapbe.documents.User;
import com.platinum.timetapbe.dto.TagStampRequest;

public interface ITagStampService {
    TagStamp saveNewTagStamp(User user, TagStampRequest request);
}
