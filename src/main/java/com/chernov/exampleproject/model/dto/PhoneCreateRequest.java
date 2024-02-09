package com.chernov.exampleproject.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(description = "Запрос на обновление телефона")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneCreateRequest {

    @Schema(description = "Идентификатор пользователя")
    @NotNull(message = "User id cannot be null")
    private Long userId;

    @Schema(description = "Телефон")
    @NotBlank
    @NotNull(message = "Phone cannot be null")
    @Pattern(regexp = "^\\d{11}$")
    private String phone;
}
