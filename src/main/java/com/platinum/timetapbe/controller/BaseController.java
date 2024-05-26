package com.platinum.timetapbe.controller;
import com.platinum.timetapbe.config.IAuthenticationFacade;
import com.platinum.timetapbe.documents.User;
import com.platinum.timetapbe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @Autowired
    IAuthenticationFacade authenticationFacade;

    @Autowired
    private IUserService userService;

    protected User getLoggedId(){
        Authentication authentication = authenticationFacade.getAuthentication();
        String email = authentication.getName();
        return userService.getUserFromEmail(email);
    }

}
