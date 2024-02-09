package com.chernov.exampleproject.controller;

import com.chernov.exampleproject.model.dto.EmailDataDto;
import com.chernov.exampleproject.model.dto.MailCreateRequest;
import com.chernov.exampleproject.model.dto.MailUpdateRequest;
import com.chernov.exampleproject.service.UserMailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Tag(
        name = "UserEmailController", description = "Контроллер для работы с данными о эл.почте пользователя"
)
@Validated
@RestController
@RequestMapping("api/v1/email")
@RequiredArgsConstructor
public class UserEmailController {

    private final UserMailService userMailService;

    @Operation(
            summary = "Добавление почты пользователю",
            description = "Метод для добавления эл.почты пользователю"
    )
    @PostMapping
    public EmailDataDto create(@Valid @RequestBody MailCreateRequest createRequest) {
        return userMailService.createIfNotExists(createRequest);
    }

    @Operation(
            summary = "Обновить эл.почту",
            description = "Метод для обновления эл.почты по идентификатору"
    )
    @PutMapping
    public EmailDataDto update(@Valid @RequestBody MailUpdateRequest updateRequest) {
        return userMailService.update(updateRequest.getEmailId(), updateRequest.getEmail());
    }

    @Operation(
            summary = "Удалить эл.почту",
            description = "Метод для удаления эл.почты по идентификатору"
    )
    @DeleteMapping
    public void removeById(@NotNull @RequestParam(name = "userId") Long userId,
                           @NotNull @RequestParam(name = "emailId") Long emailId) {
        userMailService.removeBy(userId, emailId);
    }
}
