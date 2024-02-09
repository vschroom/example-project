package com.chernov.exampleproject.controller;

import com.chernov.exampleproject.model.auth.AuthRequest;
import com.chernov.exampleproject.model.auth.TokenResponse;
import com.chernov.exampleproject.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@Tag(name = "AuthController", description = "Контроллер идентификации и аутентификации")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Аутентификация пользователя",
            description = "Метод для аутентификации пользователя и получения jwt токена"
    )
    @PostMapping("login")
    public TokenResponse auth(@Valid @RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }
}
