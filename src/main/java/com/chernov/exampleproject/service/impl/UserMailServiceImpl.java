package com.chernov.exampleproject.service.impl;

import com.chernov.exampleproject.annotation.FilterCacheEvict;
import com.chernov.exampleproject.exception.EmailAlreadyExistsException;
import com.chernov.exampleproject.exception.EmailNotFoundException;
import com.chernov.exampleproject.model.dto.EmailDataDto;
import com.chernov.exampleproject.model.dto.MailCreateRequest;
import com.chernov.exampleproject.model.entity.EmailData;
import com.chernov.exampleproject.model.entity.User;
import com.chernov.exampleproject.repositoy.EmailDataRepository;
import com.chernov.exampleproject.service.UserMailService;
import com.chernov.exampleproject.service.UserService;
import com.chernov.exampleproject.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@FilterCacheEvict
@Service
@RequiredArgsConstructor
public class UserMailServiceImpl implements UserMailService {

    private final UserService userService;
    private final EmailDataRepository emailDataRepository;

    @Override
    @Transactional
    public EmailDataDto createIfNotExists(MailCreateRequest request) {
        String email = request.getEmail();
        validateExistence(email);

        Long userId = request.getUserId();
        User user = userService.findBy(userId);
        EmailData emailData = emailDataRepository.save(
                EmailData.builder()
                .email(email)
                .user(user)
                .build());

        return MapperUtils.toEmailDataDto(emailData);
    }

    @Override
    @Transactional
    public void removeBy(Long userId, Long emailId) {
        int emailCount = userService.getEmailCount(userId);
        if (emailCount <= 1) {
            throw new IllegalStateException(format("У пользователя %s должен быть хотя бы одна эл.почта; удаление невозможно", userId));
        }
        emailDataRepository.deleteByIdAndUserId(emailId, userId);
    }

    @Override
    @Transactional
    public EmailDataDto update(Long id, String email) {
        EmailData emailData = findById(id);
        emailData.setEmail(email);

        return MapperUtils.toEmailDataDto(emailData);
    }

    private EmailData findById(Long id) {
        return emailDataRepository.findById(id)
                .orElseThrow(EmailNotFoundException::new);
    }

    private void validateExistence(String email) {
        boolean emailExists = emailDataRepository.existsByEmail(email);
        if (emailExists) {
            throw new EmailAlreadyExistsException();
        }
    }
}
