# GST Billing, Invoice, and Tax Management Backend

## Scope
- Simple Spring Boot backend for GST billing features
- Persistence-ready backend with JPA + Flyway migrations
- No complex architecture frameworks
- Frontend separate and out of scope

## Architecture Direction
- Everything stays inside `src/main/java`
- `api` for endpoints and DTOs
- `domain` for core business models and rules
- `application` for service layer (business orchestration)
- `infrastructure` for persistence adapters and entities
- Keep architecture practical and avoid unnecessary layers

## Engineering Rules
- Keep classes small and readable
- Prefer constructor injection
- Keep domain logic inside domain classes
- Avoid unnecessary interfaces and extra layers

## Current Implementation

Active packages in `src/main/java/com/amanna/billingmanagement`
- `api.invoice` with `InvoiceController` and DTOs
- `application` with `InvoiceService` orchestrating domain + persistence
- `domain.invoice` with `Invoice`
- `infrastructure.persistence` with JPA entities and repositories
- `POST /api/v1/invoices` returns taxable amount, CGST, SGST, total tax, and total amount
- `GET /api/v1/invoices/{id}` fetches invoice by id
- `GET /api/v1/invoices` lists invoices
- `GET /api/v1/invoices?status=DRAFT|ISSUED|CANCELLED` filters invoices by status
- `POST /api/v1/invoices/{id}/issue` marks an invoice as issued
- `POST /api/v1/invoices/{id}/cancel` marks an invoice as cancelled
- `POST /api/v1/invoices/{id}/update` updates draft invoice details

Dependency direction applied
- `api` depends on `domain`
- `domain` is framework-agnostic

## Features Implemented
- Invoice create (DRAFT status)
- Invoice fetch by id
- Invoice list (with optional status filter)
- Invoice lifecycle: DRAFT → ISSUED, DRAFT → CANCELLED
- GST breakdown: CGST (9%), SGST (9%), total tax, total amount
- Invoice update (draft only)
- Validation for create/update payloads
- Default Spring error handling (minimal)
- JPA persistence through H2 + Flyway migration

## Database Setup
- H2 in-memory for development (default)
- PostgreSQL for production (set via environment)
- Flyway migrations auto-run from `src/main/resources/db/migration/`

## How to Run

Build locally
```powershell
./mvnw clean package
```

This project is intentionally minimal, so there are no test files at the moment.

## Next Steps

- Add invoice line items and product/HSN support
- Add IGST/place-of-supply rules and configurable tax rates
- Add auth/roles, audit trails, and reporting exports
- Add tests for domain and API lifecycle flows

