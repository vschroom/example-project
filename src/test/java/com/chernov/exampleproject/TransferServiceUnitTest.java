package com.chernov.exampleproject;

import com.chernov.exampleproject.model.dto.TransferRequest;
import com.chernov.exampleproject.model.entity.Account;
import com.chernov.exampleproject.model.entity.User;
import com.chernov.exampleproject.repositoy.AccountRepository;
import com.chernov.exampleproject.service.TransferService;
import com.chernov.exampleproject.service.UserService;
import com.chernov.exampleproject.service.impl.TransferServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.redisson.RedissonLock;
import org.redisson.api.RedissonClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.util.Optional.of;
import static org.mockito.Mockito.*;

public class TransferServiceUnitTest {

    private final UserService userService = Mockito.mock(UserService.class);
    private final AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
    private final RedissonClient redissonClient = Mockito.mock(RedissonClient.class);
    private final RedissonLock redissonLock = Mockito.mock(RedissonLock.class);

    private final TransferService transferService = new TransferServiceImpl(accountRepository, userService, redissonClient);

    @Test
    void shouldTransferBalance() {
        TransferRequest transferRequest = new TransferRequest(2L, BigDecimal.TEN);
        User user = buildUser();
        Account accountFrom = buildAccount(1L);
        Account accountTo = buildAccount(2L);
        List<Account> accounts = List.of(accountFrom, accountTo);

        when(redissonClient.getLock(anyString())).thenReturn(redissonLock);
        when(userService.getCurrentUser()).thenReturn(user);
        when(accountRepository.findByUserId(1L)).thenReturn(of(accountFrom));
        when(accountRepository.findByUserId(2L)).thenReturn(of(accountTo));
        when(accountRepository.saveAllAndFlush(anyList())).thenReturn(accounts);

        transferService.transferBalanceFromCurrentUserAccount(transferRequest);

        verify(redissonClient).getLock("transfer");
        verify(userService).getCurrentUser();
        verify(accountRepository).findByUserId(1L);
        verify(accountRepository).findByUserId(2L);
        verify(accountRepository).saveAllAndFlush(accounts);
        verifyNoMoreInteractions(redissonClient, userService, accountRepository);

        Assertions.assertThat(accountFrom).isNotNull();
        Assertions.assertThat(accountFrom.getBalance()).isLessThan(accountTo.getBalance());
        Assertions.assertThat(accountFrom.getBalance()).isEqualTo(BigDecimal.valueOf(90));
        Assertions.assertThat(accountTo.getBalance()).isEqualTo(BigDecimal.valueOf(110));
    }

    private User buildUser() {
        return User.builder()
                .id(1L)
                .name("Jack")
                .dateOfBirth(LocalDate.of(1996, 4, 1))
                .build();
    }

    private Account buildAccount(Long id) {
        return Account.builder()
                .id(id)
                .user(new User())
                .balance(BigDecimal.valueOf(100))
                .initialBalance(BigDecimal.valueOf(100))
                .build();
    }
}
