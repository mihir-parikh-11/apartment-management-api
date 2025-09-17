package com.apartment.management.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * A TransactionType Enum.
 */
@Getter
@RequiredArgsConstructor
public enum TransactionType {
    CREDIT("Credit"), DEBIT("Debit");

    private final String description;
}
