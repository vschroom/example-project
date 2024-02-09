package com.chernov.exampleproject.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Schema(description = "Дто пользователя")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Имя")
    private String name;
    @Schema(description = "Дата рождения")
    private LocalDate dateOfBirth;
    @Schema(description = "Данные о телефонах")
    private Set<PhoneDataDto> phoneData;
    @Schema(description = "Данные о эл.почте")
    private Set<EmailDataDto> emailData;
}
