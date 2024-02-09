package com.chernov.exampleproject.service;

import com.chernov.exampleproject.model.dto.TransferRequest;

public interface TransferService {

    void transferBalanceFromCurrentUserAccount(TransferRequest request);
}
