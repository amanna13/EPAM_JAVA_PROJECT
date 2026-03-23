package com.amanna.billingmanagement.api.invoice;

import com.amanna.billingmanagement.api.invoice.dto.CreateInvoiceRequest;
import com.amanna.billingmanagement.api.invoice.dto.InvoiceResponse;
import com.amanna.billingmanagement.domain.invoice.Invoice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceResponse create(@RequestBody CreateInvoiceRequest request) {
        Invoice invoice = Invoice.draft(request.customerGstin(), request.taxableAmount());
        return new InvoiceResponse(
                invoice.id().value(),
                invoice.customerGstin(),
                invoice.taxableAmount(),
                invoice.createdAt()
        );
    }
}

