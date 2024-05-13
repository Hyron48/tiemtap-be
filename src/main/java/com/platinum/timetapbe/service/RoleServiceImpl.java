package com.platinum.timetapbe.service;

import com.platinum.timetapbe.documents.Role;
import com.platinum.timetapbe.documents.User;
import com.platinum.timetapbe.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRole(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<String> getRoleFromUser(User user) {
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(role -> roles.add(role.getName()));
        return roles;
    }
}

