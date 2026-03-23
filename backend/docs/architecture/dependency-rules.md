# Dependency Direction Rules

## Scope
Simple dependency rules for the backend package structure.

## Allowed Dependencies
- `api` can depend on `domain` and `shared`
- `domain` can depend on `shared` only
- `infrastructure` can depend on `domain` and `shared`
- `shared` must not depend on project packages

## Boundary Notes
- API DTOs must not be used as domain entities
- Domain objects must not import Spring framework classes
- Keep domain logic pure without Spring annotations

## Conventions
- Package naming follows `com.amanna.billingmanagement.<layer>`
- Constructor injection is preferred for API and adapter classes
- Domain layer is immutable first and invariant driven

