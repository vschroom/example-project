package com.chernov.exampleproject.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(description = "Запрос на создание эл.почты")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailUpdateRequest {

    @Schema(description = "Идентификатор эл.почты")
    @NotNull(message = "Email id cannot be null")
    private Long emailId;

    @Schema(description = "Эл.почта")
    @NotBlank
    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^(.+)@(\\S+)$")
    private String email;
}
