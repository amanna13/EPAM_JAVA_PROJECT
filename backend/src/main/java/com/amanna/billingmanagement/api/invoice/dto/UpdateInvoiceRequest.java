package com.amanna.billingmanagement.api.invoice.dto;

import java.math.BigDecimal;

public record UpdateInvoiceRequest(String customerGstin, BigDecimal taxableAmount) {
}

