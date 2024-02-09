package com.chernov.exampleproject.service;

import com.chernov.exampleproject.model.dto.UserDto;
import com.chernov.exampleproject.model.dto.UserFilterDto;

import java.util.List;

public interface UserFilterService {

    List<UserDto> findBy(UserFilterDto filter);
}
