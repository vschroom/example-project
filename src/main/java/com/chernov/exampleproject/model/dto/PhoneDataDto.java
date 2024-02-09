package com.chernov.exampleproject.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Данные телефона")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDataDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Идентификатор пользователя")
    private String userId;
    @Schema(description = "Номер телефона")
    private String phone;
}
