package com.chernov.exampleproject.controller;

import com.chernov.exampleproject.model.dto.TransferRequest;
import com.chernov.exampleproject.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(
        name = "TransferController", description = "Контроллер для трансфера баланса"
)
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/transfer")
public class TransferController {

    private final TransferService transferService;

    @Operation(
            summary = "Трансфер",
            description = "Трансфер баланса от текущего пользователя к указанному"
    )
    @PostMapping
    public void transfer(@Valid @RequestBody TransferRequest transferRequest) {
        transferService.transferBalanceFromCurrentUserAccount(transferRequest);
    }
}
