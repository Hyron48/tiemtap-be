package com.platinum.timetapbe.config;

import com.platinum.timetapbe.documents.Privilege;
import com.platinum.timetapbe.documents.Role;
import com.platinum.timetapbe.repository.PrivilegeRepository;
import com.platinum.timetapbe.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataConfig implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Value("${roleUser}")
    private String roleUser;
    @Value("${roleAdmin}")
    private String roleAdmin;

    @Value("${readPermission}")
    private String readPermission;
    @Value("${writePermission}")
    private String writePermission;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event){
        if(alreadySetup)
            return;
        Privilege readPrivilege = createPrivilegeIfNotFound(readPermission);
        Privilege writePrivilege = createPrivilegeIfNotFound(writePermission);


        List<Privilege> userPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        createRoleIfNotFound(roleUser, userPrivileges);
        createRoleIfNotFound(roleAdmin, adminPrivileges);

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name){
        Privilege privilege = privilegeRepository.findByName(name);

        if (privilege == null){
            privilege = new Privilege();
            privilege.setName(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges){
        Role role = roleRepository.findByName(name);

        if(role == null){
            role = new Role();
            role.setName(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
