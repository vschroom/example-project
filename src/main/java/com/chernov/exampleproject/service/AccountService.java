package com.chernov.exampleproject.service;

import com.chernov.exampleproject.model.entity.Account;
import com.chernov.exampleproject.model.entity.User;

public interface AccountService {

    Account createAccount(User user);

    void increaseEveryBalanceToFixedPercent();
}
