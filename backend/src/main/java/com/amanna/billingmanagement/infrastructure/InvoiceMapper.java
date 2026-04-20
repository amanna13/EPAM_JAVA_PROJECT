package com.amanna.billingmanagement.infrastructure;

import com.amanna.billingmanagement.domain.invoice.Invoice;
import com.amanna.billingmanagement.infrastructure.persistence.entity.InvoiceEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class InvoiceMapper {

	public Invoice toDomain(InvoiceEntity entity) {
		return Invoice.reconstruct(
				entity.getId(),
				entity.getCustomerGstin(),
				entity.getTaxableAmount(),
				entity.getStatus(),
				entity.getCreatedAt()
		);
	}

	public InvoiceEntity toEntity(Invoice invoice) {
		InvoiceEntity entity = new InvoiceEntity();
		entity.setId(invoice.id());
		entity.setCustomerGstin(invoice.customerGstin());
		entity.setTaxableAmount(invoice.taxableAmount());
		entity.setStatus(invoice.status());
		entity.setCreatedAt(invoice.createdAt());
		entity.setUpdatedAt(Instant.now());
		return entity;
	}
}

