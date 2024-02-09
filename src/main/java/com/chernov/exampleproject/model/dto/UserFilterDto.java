package com.chernov.exampleproject.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Schema(description = "Запрос на поиск по значениям")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFilterDto {

    @Schema(description = "Поиск всех пользователей, кто родился после указанной даты")
    private LocalDate dateOfBirth;
    @Schema(description = "Поиск по номеру телефона")
    private String phone;
    @Schema(description = "Поиск по имени")
    private String name;
    @Schema(description = "Поиск по эл.почте")
    private String email;
    @NotNull
    @Schema(description = "Пагинация")
    private PaginationDto pagination;
}
