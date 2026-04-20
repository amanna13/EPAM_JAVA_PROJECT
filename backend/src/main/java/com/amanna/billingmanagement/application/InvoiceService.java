package com.amanna.billingmanagement.application;

import com.amanna.billingmanagement.domain.invoice.Invoice;
import com.amanna.billingmanagement.domain.invoice.InvoiceStatus;
import com.amanna.billingmanagement.infrastructure.InvoiceMapper;
import com.amanna.billingmanagement.infrastructure.persistence.repository.InvoiceJpaRepository;
import com.amanna.billingmanagement.infrastructure.persistence.entity.InvoiceEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InvoiceService {

	private final InvoiceJpaRepository invoiceJpaRepository;
	private final InvoiceMapper invoiceMapper;

	public InvoiceService(InvoiceJpaRepository invoiceJpaRepository, InvoiceMapper invoiceMapper) {
		this.invoiceJpaRepository = invoiceJpaRepository;
		this.invoiceMapper = invoiceMapper;
	}

	public Invoice createDraft(String customerGstin, BigDecimal taxableAmount) {
		Invoice invoice = Invoice.draft(customerGstin, taxableAmount);
		InvoiceEntity saved = invoiceJpaRepository.save(invoiceMapper.toEntity(invoice));
		return invoiceMapper.toDomain(saved);
	}

	public Invoice getById(String id) {
		InvoiceEntity entity = invoiceJpaRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found: " + id));
		return invoiceMapper.toDomain(entity);
	}

	public List<Invoice> list(InvoiceStatus status) {
		List<InvoiceEntity> entities = status == null
				? invoiceJpaRepository.findAllByOrderByCreatedAtAsc()
				: invoiceJpaRepository.findByStatusOrderByCreatedAtAsc(status);
		return entities.stream().map(invoiceMapper::toDomain).toList();
	}

	public Invoice issue(String id) {
		Invoice invoice = getById(id).issue();
		InvoiceEntity saved = invoiceJpaRepository.save(invoiceMapper.toEntity(invoice));
		return invoiceMapper.toDomain(saved);
	}

	public Invoice cancel(String id) {
		Invoice invoice = getById(id).cancel();
		InvoiceEntity saved = invoiceJpaRepository.save(invoiceMapper.toEntity(invoice));
		return invoiceMapper.toDomain(saved);
	}

	public Invoice update(String id, String customerGstin, BigDecimal taxableAmount) {
		Invoice invoice = getById(id).update(customerGstin, taxableAmount);
		InvoiceEntity saved = invoiceJpaRepository.save(invoiceMapper.toEntity(invoice));
		return invoiceMapper.toDomain(saved);
	}
}

