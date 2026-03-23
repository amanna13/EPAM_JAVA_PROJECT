package com.amanna.billingmanagement.api.invoice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public record CreateInvoiceRequest(
		@NotBlank(message = "Customer GSTIN is required")
		String customerGstin,
		@DecimalMin(value = "0.01", message = "Taxable amount must be greater than zero")
		BigDecimal taxableAmount
) {
}

