package com.chernov.exampleproject.controller;

import com.chernov.exampleproject.model.dto.PhoneCreateRequest;
import com.chernov.exampleproject.model.dto.PhoneDataDto;
import com.chernov.exampleproject.model.dto.PhoneUpdateRequest;
import com.chernov.exampleproject.service.PhoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Tag(
        name = "PhoneController", description = "Контроллер для работы с данными о телефонах пользователя"
)
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/phone")
public class PhoneController {

    private final PhoneService phoneService;

    @Operation(
            summary = "Добавление телефона пользователю",
            description = "Метод для добавления телефона пользователю"
    )
    @PostMapping
    public PhoneDataDto create(@Valid @RequestBody PhoneCreateRequest phoneRequestDto) {
        return phoneService.createIfNotExists(phoneRequestDto);
    }

    @Operation(
            summary = "Обновить телефон",
            description = "Метод для обновления телефона по идентификатору"
    )
    @PutMapping
    public PhoneDataDto update(@Valid @RequestBody PhoneUpdateRequest requestDto) {
        return phoneService.update(requestDto.getPhoneId(), requestDto.getPhone());
    }

    @Operation(
            summary = "Удалить телефон",
            description = "Метод для удаления телефона по идентификатору"
    )
    @DeleteMapping
    public void removeById(@NotNull @RequestParam(name = "userId") Long userId,
                           @NotNull @RequestParam(name = "phoneId") Long phoneId) {
        phoneService.removeBy(userId, phoneId);
    }
}
