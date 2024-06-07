package com.platinum.timetapbe.controller;

import com.platinum.timetapbe.documents.TagStamp;
import com.platinum.timetapbe.documents.User;
import com.platinum.timetapbe.dto.TagStampRequest;
import com.platinum.timetapbe.service.ITagStampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagStampController extends BaseController {

    @Autowired
    private ITagStampService tagStampService;

    @PostMapping("/tag-stamp")
    public ResponseEntity<TagStamp> registerNewTagStamp(@RequestBody TagStampRequest request) {
        User user = getLoggedId();
        return new ResponseEntity<>(tagStampService.saveNewTagStamp(user, request),HttpStatus.OK);
    }

    @GetMapping("/tag-history")
    public ResponseEntity<List<TagStamp>> registerNewTagStamp() {
        User user = getLoggedId();
        return new ResponseEntity<>(tagStampService.getTagHistory(user),HttpStatus.OK);
    }
}
