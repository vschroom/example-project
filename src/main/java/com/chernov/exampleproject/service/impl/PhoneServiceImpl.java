package com.chernov.exampleproject.service.impl;

import com.chernov.exampleproject.annotation.FilterCacheEvict;
import com.chernov.exampleproject.exception.PhoneAlreadyExistsException;
import com.chernov.exampleproject.exception.PhoneNotFoundException;
import com.chernov.exampleproject.model.dto.PhoneDataDto;
import com.chernov.exampleproject.model.dto.PhoneCreateRequest;
import com.chernov.exampleproject.model.entity.PhoneData;
import com.chernov.exampleproject.model.entity.User;
import com.chernov.exampleproject.repositoy.PhoneDataRepository;
import com.chernov.exampleproject.service.PhoneService;
import com.chernov.exampleproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.chernov.exampleproject.utils.MapperUtils.toPhoneDataDto;

@FilterCacheEvict
@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    private final UserService userService;
    private final PhoneDataRepository phoneDataRepository;

    @Override
    @Transactional
    public PhoneDataDto createIfNotExists(PhoneCreateRequest request) {
        String phone = request.getPhone();
        Long userId = request.getUserId();
        validateExistence(phone);

        User user = userService.findBy(userId);
        PhoneData phoneData = phoneDataRepository.save(
                PhoneData.builder()
                .user(user)
                .phone(phone)
                .build());

        return toPhoneDataDto(phoneData);
    }

    @Override
    @Transactional
    public void removeBy(Long userId, Long phoneId) {
        int phoneCount = userService.getPhoneCount(userId);
        if (phoneCount <= 1) {
            throw new IllegalStateException(String.format("У пользователя %s должен быть хотя бы один телефон; удаление невозможно", userId));
        }

        phoneDataRepository.deleteByIdAndUserId(phoneId, userId);
    }

    @Override
    @Transactional
    public PhoneDataDto update(Long id, String phone) {
        PhoneData phoneData = findById(id);
        phoneData.setPhone(phone);

        return toPhoneDataDto(phoneData);
    }

    private PhoneData findById(Long id) {
        return phoneDataRepository.findById(id)
                .orElseThrow(() -> new PhoneNotFoundException(id));
    }

    private void validateExistence(String phone) {
        boolean phoneExists = phoneDataRepository.existsByPhone(phone);
        if (phoneExists) {
            throw new PhoneAlreadyExistsException(phone);
        }
    }
}
