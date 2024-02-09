package com.chernov.exampleproject.service;

import com.chernov.exampleproject.model.entity.User;

public interface UserService {

    User findBy(Long userId);

    int getPhoneCount(Long userId);

    int getEmailCount(Long userId);

    User getCurrentUser();
}
