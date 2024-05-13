package com.platinum.timetapbe.security;

import com.platinum.timetapbe.documents.Privilege;
import com.platinum.timetapbe.documents.Role;
import com.platinum.timetapbe.documents.User;
import com.platinum.timetapbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws RuntimeException{

        User user = userRepository.findByEmail(email).get();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isVerified(),
                true,
                true,
                true,
                getAuthorities(user.getRoles()));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Collection<Role>roles){
        return getGrantedAuthorities(getPrivileges(roles));
    }

    public List<String> getPrivileges(Collection<Role> roles){

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();

        for (Role role : roles){
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }

        for (Privilege item : collection){
            privileges.add(item.getName());
        }

        return privileges;
    }

    public List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
