package com.chernov.exampleproject.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(description = "Запрос на создание эл.почты")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailCreateRequest {

    @Schema(description = "Идентификатор пользователя")
    @NotNull(message = "User id cannot be null")
    private Long userId;

    @Schema(description = "Эл.почта")
    @NotBlank
    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^(.+)@(\\S+)$")
    private String email;
}
