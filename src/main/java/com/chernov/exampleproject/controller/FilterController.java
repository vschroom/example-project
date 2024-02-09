package com.chernov.exampleproject.controller;

import com.chernov.exampleproject.model.dto.UserDto;
import com.chernov.exampleproject.model.dto.UserFilterDto;
import com.chernov.exampleproject.service.UserFilterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Tag(
        name = "FilterController", description = "Контроллер для общих запросов с фильтрацией"
)
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user/filter")
public class FilterController {

    private final UserFilterService userFilterService;

    @Operation(
            summary = "Поиск по фильтру",
            description = "Метод для поиска по фильтру"
    )
    @PostMapping
    public List<UserDto> findAllByFilter(@Valid @RequestBody UserFilterDto filter) {
        return userFilterService.findBy(filter);
    }
}
