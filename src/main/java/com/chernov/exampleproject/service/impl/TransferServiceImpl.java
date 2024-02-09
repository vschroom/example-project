package com.chernov.exampleproject.service.impl;

import com.chernov.exampleproject.exception.AccountNotFoundException;
import com.chernov.exampleproject.model.dto.TransferRequest;
import com.chernov.exampleproject.model.entity.Account;
import com.chernov.exampleproject.repositoy.AccountRepository;
import com.chernov.exampleproject.service.TransferService;
import com.chernov.exampleproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void transferBalanceFromCurrentUserAccount(TransferRequest request) {
        Long userIdFrom = userService.getCurrentUser().getId();
        Account accountFrom = findByUser(userIdFrom);
        Account accountTo = findByUser(request.getUserIdTo());
        BigDecimal balanceFrom = accountFrom.getBalance();
        BigDecimal balanceTo = accountTo.getBalance();

        BigDecimal sumValue = request.getSumValue();
        BigDecimal subtractedBalanceFrom = balanceFrom.subtract(sumValue);
        if (subtractedBalanceFrom.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("Balance cannot be less then 0");
        }
        BigDecimal addedBalanceTo = balanceTo.add(sumValue);
        accountFrom.setBalance(subtractedBalanceFrom);
        accountTo.setBalance(addedBalanceTo);
        log.info("User with id {} transfer {} balance to user with id {} at {} ",
                userIdFrom, sumValue, request.getUserIdTo(), LocalDateTime.now());
    }

    public Account findByUser(Long id) {
        return accountRepository.findByUserId(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }
}
