package com.amanna.billingmanagement.api.invoice;

import com.amanna.billingmanagement.api.invoice.dto.CreateInvoiceRequest;
import com.amanna.billingmanagement.api.invoice.dto.InvoiceResponse;
import com.amanna.billingmanagement.api.invoice.dto.UpdateInvoiceRequest;
import com.amanna.billingmanagement.domain.invoice.Invoice;
import com.amanna.billingmanagement.domain.invoice.InvoiceStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final Map<String, Invoice> invoices = new ConcurrentHashMap<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceResponse create(@RequestBody CreateInvoiceRequest request) {
        Invoice invoice = Invoice.draft(request.customerGstin(), request.taxableAmount());
        invoices.put(invoice.id(), invoice);
        return toResponse(invoice);
    }

    @GetMapping("/{id}")
    public InvoiceResponse getById(@PathVariable String id) {
        Invoice invoice = invoices.get(id);
        if (invoice == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found: " + id);
        }
        return toResponse(invoice);
    }

    @GetMapping
    public List<InvoiceResponse> list(@RequestParam(required = false) InvoiceStatus status) {
        return invoices.values().stream()
                .filter(invoice -> status == null || invoice.status() == status)
                .sorted((first, second) -> first.createdAt().compareTo(second.createdAt()))
                .map(this::toResponse)
                .toList();
    }

    @PostMapping("/{id}/cancel")
    public InvoiceResponse cancel(@PathVariable String id) {
        Invoice invoice = invoices.get(id);
        if (invoice == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found: " + id);
        }
        Invoice cancelledInvoice = invoice.cancel();
        invoices.put(id, cancelledInvoice);
        return toResponse(cancelledInvoice);
    }

    @PostMapping("/{id}/issue")
    public InvoiceResponse issue(@PathVariable String id) {
        Invoice invoice = invoices.get(id);
        if (invoice == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found: " + id);
        }
        Invoice issuedInvoice = invoice.issue();
        invoices.put(id, issuedInvoice);
        return toResponse(issuedInvoice);
    }

    @PostMapping("/{id}/update")
    public InvoiceResponse update(@PathVariable String id, @RequestBody UpdateInvoiceRequest request) {
        Invoice invoice = invoices.get(id);
        if (invoice == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found: " + id);
        }
        Invoice updatedInvoice = invoice.update(request.customerGstin(), request.taxableAmount());
        invoices.put(id, updatedInvoice);
        return toResponse(updatedInvoice);
    }

    private InvoiceResponse toResponse(Invoice invoice) {
        return new InvoiceResponse(
            invoice.id(),
            invoice.customerGstin(),
            invoice.taxableAmount(),
            invoice.cgstAmount(),
            invoice.sgstAmount(),
            invoice.totalTaxAmount(),
            invoice.totalAmount(),
            invoice.status().name(),
            invoice.createdAt()
        );
    }
}

