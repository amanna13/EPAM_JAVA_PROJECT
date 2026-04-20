package com.amanna.billingmanagement.api.invoice.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record InvoiceResponse(
		String id,
		String customerGstin,
		BigDecimal taxableAmount,
		BigDecimal cgstAmount,
		BigDecimal sgstAmount,
		BigDecimal totalTaxAmount,
		BigDecimal totalAmount,
		Instant createdAt
) {
}

