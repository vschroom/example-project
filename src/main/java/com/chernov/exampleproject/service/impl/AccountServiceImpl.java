package com.chernov.exampleproject.service.impl;

import com.chernov.exampleproject.model.entity.Account;
import com.chernov.exampleproject.model.entity.User;
import com.chernov.exampleproject.repositoy.AccountRepository;
import com.chernov.exampleproject.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Value("${account.max-percentage:2.07}")
    private BigDecimal maxPercentage;
    @Value("${account.step-percentage:0.1}")
    private BigDecimal stepPercentage;

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Account createAccount(User user) {
        Account account = Account.builder()
                .user(user)
                .balance(BigDecimal.valueOf(100))
                .initialBalance(BigDecimal.valueOf(100))
                .build();

        return accountRepository.save(account);
    }

    @Transactional
    public void increaseEveryBalanceToFixedPercent() {
        accountRepository.findAll()
                .forEach(this::increaseBalance);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void increaseBalance(Account account) {
        BigDecimal balance = account.getBalance();
        BigDecimal initialBalance = account.getInitialBalance();
        BigDecimal maxBalance = initialBalance.add(initialBalance.multiply(maxPercentage));

        BigDecimal increasedBalance = balance.multiply(stepPercentage).add(balance);
        if (increasedBalance.compareTo(maxBalance) <= 0) {
            account.setBalance(increasedBalance);
        }
    }
}
