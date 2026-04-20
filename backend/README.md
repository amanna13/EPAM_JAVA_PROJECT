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
- No `application`, `usecase`, or `port` layers for now

## Engineering Rules
- Keep classes small and readable
- Prefer constructor injection
- Keep domain logic inside domain classes
- Avoid unnecessary interfaces and extra layers

## Current Implementation

Active packages in `src/main/java/com/amanna/billingmanagement`
- `api.invoice` with `InvoiceController` and DTOs
- `api.error` with global exception handling
- `domain.invoice` with `Invoice`
- `shared.kernel` with base domain exception

Dependency direction applied
- `api` depends on `domain`
- `domain` depends only on `shared`

## How to Run

Build locally
```powershell
./mvnw clean package
```

This project is intentionally minimal, so there are no test files at the moment.

## Next Steps

- Add persistence only when required by features
- Add more invoice operations like update, issue, cancel
- Add GST tax calculation rules in domain

