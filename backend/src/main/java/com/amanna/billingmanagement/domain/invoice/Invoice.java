package com.amanna.billingmanagement.domain.invoice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.UUID;

public final class Invoice {

    private static final BigDecimal CGST_RATE = new BigDecimal("0.09");
    private static final BigDecimal SGST_RATE = new BigDecimal("0.09");

    private final String id;
    private final String customerGstin;
    private final BigDecimal taxableAmount;
    private final Instant createdAt;
    private final InvoiceStatus status;

    private Invoice(String id, String customerGstin, BigDecimal taxableAmount, Instant createdAt, InvoiceStatus status) {
        this.id = id;
        this.customerGstin = customerGstin;
        this.taxableAmount = taxableAmount;
        this.createdAt = createdAt;
        this.status = status;
    }

    public static Invoice draft(String customerGstin, BigDecimal taxableAmount) {
        if (customerGstin == null || customerGstin.isBlank()) {
            throw new IllegalArgumentException("Customer GSTIN is required");
        }
        if (taxableAmount == null || taxableAmount.signum() <= 0) {
            throw new IllegalArgumentException("Taxable amount must be greater than zero");
        }
        return new Invoice(UUID.randomUUID().toString(), customerGstin, taxableAmount, Instant.now(), InvoiceStatus.DRAFT);
    }

    public String id() {
        return id;
    }

    public String customerGstin() {
        return customerGstin;
    }

    public BigDecimal taxableAmount() {
        return taxableAmount;
    }

    public BigDecimal cgstAmount() {
        return percentageOfTaxable(CGST_RATE);
    }

    public BigDecimal sgstAmount() {
        return percentageOfTaxable(SGST_RATE);
    }

    public BigDecimal totalTaxAmount() {
        return cgstAmount().add(sgstAmount());
    }

    public BigDecimal totalAmount() {
        return taxableAmount.add(totalTaxAmount()).setScale(2, RoundingMode.HALF_UP);
    }

    public Instant createdAt() {
        return createdAt;
    }

    public InvoiceStatus status() {
        return status;
    }

    public Invoice cancel() {
        if (status == InvoiceStatus.CANCELLED) {
            throw new IllegalStateException("Invoice is already cancelled");
        }
        return new Invoice(id, customerGstin, taxableAmount, createdAt, InvoiceStatus.CANCELLED);
    }

    private BigDecimal percentageOfTaxable(BigDecimal rate) {
        return taxableAmount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }
}

