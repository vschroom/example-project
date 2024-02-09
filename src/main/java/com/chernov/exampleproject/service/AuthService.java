package com.chernov.exampleproject.service;

import com.chernov.exampleproject.model.auth.AuthRequest;
import com.chernov.exampleproject.model.auth.TokenResponse;

public interface AuthService {

    TokenResponse authenticate(AuthRequest authRequest);
}
