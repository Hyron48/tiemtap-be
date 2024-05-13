package com.platinum.timetapbe.service;


import com.platinum.timetapbe.documents.Role;
import com.platinum.timetapbe.documents.User;

import java.util.List;

public interface IRoleService {
    Role getRole(String name);
    List<String> getRoleFromUser(User user);
}
