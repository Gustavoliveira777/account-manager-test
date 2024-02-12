package com.ebanx.accountmanager.utils;

import com.ebanx.accountmanager.dto.AccountDTO;
import com.ebanx.accountmanager.model.Account;

public final class Utils {
    public static AccountDTO AccountMapperToDTO(Account account){
        return AccountDTO.builder()
                .id(account.getAccountId())
                .balance(account.getBalance())
                .build();
    }
}
