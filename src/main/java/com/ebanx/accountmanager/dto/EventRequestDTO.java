package com.ebanx.accountmanager.dto;

import com.ebanx.accountmanager.enumerator.TypeActionEnumerator;

import java.math.BigDecimal;

public record EventRequestDTO(TypeActionEnumerator type, Integer destination, BigDecimal amount) {
}
