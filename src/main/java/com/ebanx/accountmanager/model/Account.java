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

    public void depositOperation(Double amount){
        this.balance = this.balance.add(new BigDecimal(amount));
    }
    public void withdrawOperation(Double amount){
        this.balance = this.balance.subtract(new BigDecimal(amount));
    }
}
