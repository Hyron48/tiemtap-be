package com.platinum.timetapbe.service;

import com.platinum.timetapbe.documents.TagStamp;
import com.platinum.timetapbe.dto.TagStampRequest;

public interface ITagStampService {
    TagStamp saveNewTagStamp(TagStampRequest request);
}
