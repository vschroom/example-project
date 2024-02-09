package com.chernov.exampleproject.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Дто пагинации")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDto {

    @Schema(description = "Cтраница")
    private int page;
    @Schema(description = "Количество")
    private int size = 10;
}
