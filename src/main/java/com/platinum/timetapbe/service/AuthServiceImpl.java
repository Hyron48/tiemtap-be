package com.platinum.timetapbe.service;

import com.platinum.timetapbe.config.JwtTokenConfig;
import com.platinum.timetapbe.documents.RefreshToken;
import com.platinum.timetapbe.documents.User;
import com.platinum.timetapbe.dto.LoginRequest;
import com.platinum.timetapbe.dto.LoginResponse;
import com.platinum.timetapbe.dto.RegisterRequest;
import com.platinum.timetapbe.dto.RegisterResponse;
import com.platinum.timetapbe.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private IRefreshTokenService refreshTokenService;

    @Autowired
    private JwtTokenConfig jwtTokenConfig;

    @Value("${permittedRoles}")
    private String[] permittedRoles;

    @Value("${roleUser}")
    private String roleUser;
    @Value("${roleAdmin}")
    private String roleAdmin;

    @Value("${readPermission}")
    private String readPermission;
    @Value("${writePermission}")
    private String writePermission;

    @Override
    public RegisterResponse createUser(RegisterRequest user) {
        return userService.createUser(user, roleUser);
    }

    @Override
    public RegisterResponse createAdmin(RegisterRequest user) {
        return userService.createUser(user, roleAdmin);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        System.out.println("ok" + loginRequest.getEmail() + "---" + loginRequest.getPassword());
        authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtTokenConfig.generateToken(userDetails);

        User user = userService.getUserFromEmail(loginRequest.getEmail());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setJwt(token);
        loginResponse.setRefreshToken(refreshToken.getRefreshToken());

        loginResponse.getUserPermissions().add(readPermission);
        if (isUserByRole(user, roleAdmin)) {
            loginResponse.getUserPermissions().add(writePermission);
        }

        return loginResponse;
    }

//    @Override
//    public String refreshToken(RefreshTokenRequest refreshTokenRequest){
//        UserDetails userDetails = refreshTokenService.getUserDetailsFromToken(refreshTokenRequest);
//        return jwtTokenConfig.generateToken(userDetails);
//    }
//
//    @Override
//    public void logout(User user) {
//        refreshTokenService.deleteRefreshTokenByUser(user);
//    }

    private void authenticate(String email, String password) throws DisabledException, BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new DisabledException("User disabled with email:" + email);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    private boolean isUserByRole(User user, String roleName) {
        AtomicBoolean isUserThisRole = new AtomicBoolean(false);
        user.getRoles().forEach(el -> {
            if (Objects.equals(el.getId(), roleService.getRole(roleName).getId())) {
                isUserThisRole.set(true);
            }
        });
        return isUserThisRole.get();
    }
}
