package com.chernov.exampleproject.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Данные эл.почты")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailDataDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Идентификатор пользователя")
    private String userId;
    @Schema(description = "Эл.почта")
    private String email;
}
