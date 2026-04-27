<div align="center">
  <h3><strong>GST Billing, Invoice, and Tax Management</strong></h3>
</div>

<p align="center">
  
  <img src="https://img.shields.io/badge/status-Prototype-orange" alt="Status" />
  <img src="https://img.shields.io/badge/version-0.0.1-blue" alt="Version" />
  <br/>
</p>

API documentation for GST billing, invoice, and tax management - https://gstinvoice.bytexl.live/swagger-ui/index.html


## Architecture Direction
- Everything stays inside `src/main/java`
- `api` for endpoints and DTOs
- `domain` for core business models and rules
- `application` for service layer (business orchestration)
- `infrastructure` for persistence adapters and entities
- Keep architecture practical and avoid unnecessary layers


## Current Implementation

Active packages in `src/main/java/com/amanna/billingmanagement`
- `api.invoice` with `InvoiceController` and DTOs
- `application` with `InvoiceService` orchestrating domain + persistence
- `domain.invoice` with `Invoice` and `InvoiceLineItem`
- `infrastructure.persistence` with JPA entities and repositories
- `POST /api/v1/invoices` creates invoice from line items
- `GET /api/v1/invoices/{id}` fetches invoice by id
- `GET /api/v1/invoices` lists invoices
- `GET /api/v1/invoices?status=DRAFT|ISSUED|CANCELLED` filters invoices by status
- `GET /api/v1/invoices?customerGstin=<GSTIN>` filters invoices by customer GSTIN
- `GET /api/v1/invoices?status=ISSUED&customerGstin=<GSTIN>` combines both filters
- `POST /api/v1/invoices/{id}/issue` marks an invoice as issued
- `POST /api/v1/invoices/{id}/cancel` marks an invoice as cancelled
- `POST /api/v1/invoices/{id}/update` updates draft invoice details
- `GET /api/v1/reports/gst-summary?from=YYYY-MM-DD&to=YYYY-MM-DD` returns CGST/SGST/IGST totals
- `GET /api/v1/reports/gst-summary/export?from=YYYY-MM-DD&to=YYYY-MM-DD` exports same summary as CSV

Dependency direction applied
- `api` depends on `domain`
- `domain` is framework-agnostic

## Features Implemented
- Invoice create (DRAFT status)
- Invoice fetch by id
- Invoice list (with optional status filter)
- Invoice list (optional status + customer GSTIN filters)
- Invoice lifecycle: DRAFT → ISSUED, DRAFT → CANCELLED
- Line-item based taxable amount calculation
- GST rule engine: intra-state => CGST+SGST, inter-state => IGST
- Invoice update (draft only)
- Validation for create/update payloads
- Default Spring error handling (minimal)
- JPA persistence through H2 migration
- Minimal audit logs for invoice create/update/issue/cancel (timestamp + invoiceId + action)
- GST summary endpoint/export by date range

## Database Setup
- H2 in-memory for development (default)

## How to Run

Build locally
```powershell
./mvnw clean package
```

Swagger / OpenAPI
```text
http://localhost:8080/swagger-ui.html
http://localhost:8080/v3/api-docs
```

This project is intentionally minimal, so there are no test files at the moment.


## Screenshots 

<img height="500" alt="image" src="https://github.com/user-attachments/assets/31d02369-317f-4be3-a303-e76f827dcf24" />
<img height="500" alt="image" src="https://github.com/user-attachments/assets/a416be6c-44ba-4cf3-a470-4f97b409ec3c" />
<img height="500" alt="image" src="https://github.com/user-attachments/assets/c5f1fc0c-7ce5-4cba-92fd-2b93696eb521" />
<img height="500" alt="image" src="https://github.com/user-attachments/assets/209c2dae-f72c-4b31-b05b-f91add91ed78" />
<img height="500" alt="image" src="https://github.com/user-attachments/assets/7d9beb63-6139-4c0f-8949-6dd31e37040a" />


