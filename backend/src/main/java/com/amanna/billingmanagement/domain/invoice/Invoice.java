package com.amanna.billingmanagement.domain.invoice;

import com.amanna.billingmanagement.shared.kernel.DomainException;

import java.math.BigDecimal;
import java.time.Instant;

public final class Invoice {

    private final InvoiceId id;
    private final String customerGstin;
    private final BigDecimal taxableAmount;
    private final Instant createdAt;

    private Invoice(InvoiceId id, String customerGstin, BigDecimal taxableAmount, Instant createdAt) {
        this.id = id;
        this.customerGstin = customerGstin;
        this.taxableAmount = taxableAmount;
        this.createdAt = createdAt;
    }

    public static Invoice draft(String customerGstin, BigDecimal taxableAmount) {
        if (customerGstin == null || customerGstin.isBlank()) {
            throw new DomainException("Customer GSTIN is required");
        }
        if (taxableAmount == null || taxableAmount.signum() <= 0) {
            throw new DomainException("Taxable amount must be greater than zero");
        }
        return new Invoice(InvoiceId.newId(), customerGstin, taxableAmount, Instant.now());
    }

    public InvoiceId id() {
        return id;
    }

    public String customerGstin() {
        return customerGstin;
    }

    public BigDecimal taxableAmount() {
        return taxableAmount;
    }

    public Instant createdAt() {
        return createdAt;
    }
}

