package com.platinum.timetapbe.controller;

import com.platinum.timetapbe.documents.TagStamp;
import com.platinum.timetapbe.dto.TagStampRequest;
import com.platinum.timetapbe.service.ITagStampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagStampController extends BaseController {


    @Autowired
    private ITagStampService tagStampService;


    @PostMapping("/tag-stamp")
    public ResponseEntity<TagStamp> registerNewTagStamp(@RequestBody TagStampRequest request) {
        return new ResponseEntity<>(tagStampService.saveNewTagStamp(request),HttpStatus.OK);
    }
}
