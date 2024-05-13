package com.platinum.timetapbe.service;

import com.platinum.timetapbe.documents.RefreshToken;
import com.platinum.timetapbe.documents.User;
import com.platinum.timetapbe.dto.RefreshTokenRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface IRefreshTokenService {

    RefreshToken createRefreshToken(User user);

    UserDetails getUserDetailsFromToken(RefreshTokenRequest refreshTokenRequest);

    void deleteRefreshTokenByUser(User user);
}
