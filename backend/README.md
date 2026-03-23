# GST Billing, Invoice, and Tax Management Backend

## Scope
- Simple Spring Boot backend for GST billing features
- Single Maven project under `src/main/java`
- No complex architecture frameworks
- Frontend separate and out of scope

## Architecture Direction
- Everything stays inside `src/main/java`
- `api` for endpoints and DTOs
- `domain` for core models and rules
- `shared` for common classes

## Engineering Rules
- Keep classes small and readable
- Prefer constructor injection
- Keep domain logic inside domain classes
- Avoid unnecessary interfaces and extra layers

## Current Implementation

Active packages in `src/main/java/com/amanna/billingmanagement`
- `api.invoice` with `InvoiceController` and DTOs
- `api.error` with global exception handling
- `domain.invoice` with `Invoice` and `InvoiceId`
- `shared.kernel` with base domain exception

Dependency direction applied
- `api` depends on `domain`
- `domain` depends only on `shared`

## How to Run

Build and test locally
```bash
./mvnw clean test
```

## Next Steps

- Add persistence layer with database tables and repository
- Add more invoice operations like update, issue, cancel
- Add GST tax calculation rules in domain
- Extend domain with Party, Product, TaxRate models

