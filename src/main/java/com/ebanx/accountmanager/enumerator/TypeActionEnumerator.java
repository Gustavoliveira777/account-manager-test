package com.ebanx.accountmanager.enumerator;

public enum TypeActionEnumerator {
    DEPOSIT("deposit"),
    WITHDRAW("withdraw"),
    TRANSFER("transfer");

    private final String VALUE;
    TypeActionEnumerator(String value){
        this.VALUE = value;
    }

    public String getValue() {
        return VALUE;
    }
}
