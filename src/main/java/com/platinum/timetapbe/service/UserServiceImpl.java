package com.platinum.timetapbe.service;

import com.platinum.timetapbe.documents.User;
import com.platinum.timetapbe.dto.RegisterRequest;
import com.platinum.timetapbe.dto.RegisterResponse;
import com.platinum.timetapbe.exception.SourceNotFoundException;
import com.platinum.timetapbe.repository.UserRepository;
import com.platinum.timetapbe.util.TokenStringHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private TokenStringHelper passwordEncoder;

    @Override
    public RegisterResponse createUser(RegisterRequest uRequest, String roles) {

        if (userRepository.existsByEmail(uRequest.getEmail())) {
            throw new RuntimeException("User already registered with email: " + uRequest.getEmail());
        }

        User user = new User();
        BeanUtils.copyProperties(uRequest, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(roleService.getRole(roles)));
        user.setVerified(true);
        userRepository.save(user);

        RegisterResponse registerResponse = new RegisterResponse();
        BeanUtils.copyProperties(user, registerResponse);
        return registerResponse;
    }

    @Override
    public User getUserFromEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new SourceNotFoundException("User not found with email: " + email));
    }
}
