package com.amanna.billingmanagement.domain.invoice;

import java.util.Objects;
import java.util.UUID;

public final class InvoiceId {

    private final String value;

    private InvoiceId(String value) {
        this.value = value;
    }

    public static InvoiceId newId() {
        return new InvoiceId(UUID.randomUUID().toString());
    }

    public static InvoiceId of(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Invoice id is required");
        }
        return new InvoiceId(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InvoiceId invoiceId)) {
            return false;
        }
        return Objects.equals(value, invoiceId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}

