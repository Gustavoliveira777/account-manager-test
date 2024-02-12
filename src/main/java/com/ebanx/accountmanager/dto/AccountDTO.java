package com.ebanx.accountmanager.dto;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AccountDTO {
    private String id;
    private BigDecimal balance;
}
