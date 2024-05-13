package com.platinum.timetapbe.service;

import com.platinum.timetapbe.documents.RefreshToken;
import com.platinum.timetapbe.documents.User;
import com.platinum.timetapbe.dto.RefreshTokenRequest;
import com.platinum.timetapbe.exception.SourceNotFoundException;
import com.platinum.timetapbe.repository.RefreshTokenRepository;
import com.platinum.timetapbe.security.CustomUserDetailService;
import com.platinum.timetapbe.util.TokenStringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private TokenStringHelper tokenStringHelper;


    @Override
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(user.getId()).orElse(new RefreshToken());
        if(refreshToken.getRefreshToken() == null) {
            refreshToken.setUser(user);
            refreshToken.setRefreshToken(tokenStringHelper.createStringToken());
        }

        return refreshTokenRepository.save(refreshToken);
    }
    @Override
    public UserDetails getUserDetailsFromToken(RefreshTokenRequest refreshTokenRequest){
        String email = findUserByRefreshToken(refreshTokenRequest.getRefreshToken());
        return  userDetailsService.loadUserByUsername(email);
    }

    @Override
    public void deleteRefreshTokenByUser(User user) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(user.getId()).orElseThrow(
                ()-> new SourceNotFoundException("RefreshToken not found for user id" + user.getId()));
        refreshTokenRepository.delete(refreshToken);
    }

    private String findUserByRefreshToken(String token){
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(token)
                .orElseThrow(() -> new SourceNotFoundException("Token error, not found in the DB"));
        return refreshToken.getUser().getEmail();
    }
}

