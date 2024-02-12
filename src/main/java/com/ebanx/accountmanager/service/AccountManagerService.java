package com.ebanx.accountmanager.service;

import com.ebanx.accountmanager.dto.EventRequestDTO;
import com.ebanx.accountmanager.dto.EventResponseDTO;
import com.ebanx.accountmanager.exception.AccountTransactionException;

import java.math.BigDecimal;

public interface AccountManagerService {
    EventResponseDTO eventHandler(EventRequestDTO request) throws AccountTransactionException;
    BigDecimal getBalance(Integer accountNumber) throws AccountTransactionException;
    void reset() throws AccountTransactionException;
}
