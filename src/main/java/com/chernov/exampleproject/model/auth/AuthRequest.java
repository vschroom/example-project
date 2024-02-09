package com.chernov.exampleproject.model.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(description = "Запрос на аутентификацию")
@Validated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @Schema(description = "Эл.почта")
    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^(.+)@(\\S+)$")
    private String email;

    @Schema(description = "Пароль")
    @NotNull(message = "Password cannot be null")
    private String password;
}
