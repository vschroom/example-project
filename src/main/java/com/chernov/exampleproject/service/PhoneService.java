package com.chernov.exampleproject.service;

import com.chernov.exampleproject.model.dto.PhoneDataDto;
import com.chernov.exampleproject.model.dto.PhoneCreateRequest;

public interface PhoneService {

    PhoneDataDto createIfNotExists(PhoneCreateRequest phoneRequestDto);

    void removeBy(Long userId, Long phoneId);

    PhoneDataDto update(Long id, String phone);
}
