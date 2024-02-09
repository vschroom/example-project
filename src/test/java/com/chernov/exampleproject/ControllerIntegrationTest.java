package com.chernov.exampleproject;

import com.chernov.exampleproject.model.entity.Account;
import com.chernov.exampleproject.model.entity.EmailData;
import com.chernov.exampleproject.model.entity.PhoneData;
import com.chernov.exampleproject.model.entity.User;
import com.chernov.exampleproject.repositoy.AccountRepository;
import com.chernov.exampleproject.repositoy.EmailDataRepository;
import com.chernov.exampleproject.repositoy.PhoneDataRepository;
import com.chernov.exampleproject.repositoy.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PhoneDataRepository phoneDataRepository;
    @Autowired
    EmailDataRepository emailDataRepository;

    @BeforeEach
    void setUp() {
        User user = userRepository.save(buildUser());
        accountRepository.save(buildAccount(user));
        phoneDataRepository.save(buildPhoneData(user));
        emailDataRepository.save(buildEmailData(user));
    }

    private User buildUser() {
        return User.builder()
                .id(1L)
                .name("Jack")
                .dateOfBirth(LocalDate.of(1996, 4, 1))
                .build();
    }

    private PhoneData buildPhoneData(User user) {
        return PhoneData.builder()
                .id(1L)
                .user(user)
                .phone("76574743366")
                .build();
    }

    private EmailData buildEmailData(User user) {
        return EmailData.builder()
                .id(1L)
                .user(user)
                .email("jack@mail.ru")
                .build();
    }

    private Account buildAccount(User user) {
        return Account.builder()
                .id(1L)
                .user(user)
                .balance(BigDecimal.ONE)
                .initialBalance(BigDecimal.ONE)
                .build();
    }
}
