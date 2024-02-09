package com.chernov.exampleproject.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "Запрос на перевод")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    @Schema(description = "Идентификатор пользователя которому осуществляется перевод")
    @NotNull(message = "User id cannot be null")
    private Long userIdTo;
    @Schema(description = "Сумма перевода")
    @NotNull(message = "sum cannot be null")
    @DecimalMin(value = "0.0", message = "sum value cannot be null")
    private BigDecimal sumValue;
}
