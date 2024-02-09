package com.chernov.exampleproject.service;

import com.chernov.exampleproject.model.dto.EmailDataDto;
import com.chernov.exampleproject.model.dto.MailCreateRequest;

public interface UserMailService {

    EmailDataDto createIfNotExists(MailCreateRequest mailCreateRequestDto);

    void removeBy(Long userId, Long emailId);

    EmailDataDto update(Long id, String email);
}
