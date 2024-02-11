package com.ebanx.accountmanager.service;

import com.ebanx.accountmanager.dto.EventRequestDTO;
import com.ebanx.accountmanager.dto.EventResponseDTO;
import com.ebanx.accountmanager.exception.AccountTransactionException;

public interface AccountManagerService {
    EventResponseDTO eventHandler(EventRequestDTO request) throws AccountTransactionException;
    Double getBalance(Integer accountNumber);
}
