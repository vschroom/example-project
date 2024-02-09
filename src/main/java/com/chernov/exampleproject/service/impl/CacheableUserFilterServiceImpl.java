package com.chernov.exampleproject.service.impl;

import com.chernov.exampleproject.model.dto.UserDto;
import com.chernov.exampleproject.model.dto.UserFilterDto;
import com.chernov.exampleproject.service.UserFilterService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class CacheableUserFilterServiceImpl implements UserFilterService {

    private final UserFilterService userFilterService;

    public CacheableUserFilterServiceImpl(@Qualifier("userFilterServiceImpl") UserFilterService userFilterService) {
        this.userFilterService = userFilterService;
    }

    @Override
    @Cacheable(value = "user_filter", key = "#filter.hashCode()")
    public List<UserDto> findBy(UserFilterDto filter) {
        return userFilterService.findBy(filter);
    }
}
