package com.ebanx.accountmanager.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    private Integer accountId;
    private BigDecimal balance;

    public void depositOperation(BigDecimal amount){
        this.balance = this.balance.add(amount);
    }
    public void withdrawOperation(BigDecimal amount){
        this.balance = this.balance.subtract(amount);
    }
}
