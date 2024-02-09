package com.chernov.exampleproject.config;

import com.chernov.exampleproject.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AccountSchedulerConfig {

    private final AccountService accountService;

    @SchedulerLock(name = "increase_balance_schedule_task")
    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    public void increaseBalance() {
        accountService.increaseEveryBalanceToFixedPercent();
    }
}
