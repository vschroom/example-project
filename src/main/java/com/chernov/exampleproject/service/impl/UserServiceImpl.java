package com.chernov.exampleproject.service.impl;

import com.chernov.exampleproject.exception.UserNotFoundException;
import com.chernov.exampleproject.model.entity.User;
import com.chernov.exampleproject.repositoy.UserRepository;
import com.chernov.exampleproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User findBy(Long id) {
        return userRepository.findEagerBy(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Transactional
    public int getPhoneCount(Long userId) {
        return userRepository.getPhoneCount(userId);
    }

    @Override
    @Transactional
    public int getEmailCount(Long userId) {
        return userRepository.getEmailCount(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(userEmail)
                .orElseThrow(UserNotFoundException::new);
    }
}
