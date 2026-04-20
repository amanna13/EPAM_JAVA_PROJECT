package com.amanna.billingmanagement.api.invoice.dto;

import java.math.BigDecimal;

public record CreateInvoiceRequest(
		String customerGstin,
		BigDecimal taxableAmount
) {
}

