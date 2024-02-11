package com.ebanx.accountmanager.service;

import com.ebanx.accountmanager.dto.EventRequestDTO;
import com.ebanx.accountmanager.dto.EventResponseDTO;

public interface AccountManagerService {
    EventResponseDTO eventHandler(EventRequestDTO request);
    Double getBalance(Integer accountNumber);
}
