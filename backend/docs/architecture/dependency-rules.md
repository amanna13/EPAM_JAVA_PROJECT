# Dependency Direction Rules

## Scope
Simple dependency rules for the backend package structure.

## Allowed Dependencies
- `api` can depend on `domain`
- `domain` should not depend on Spring or web packages

## Not Allowed (for this project stage)
- `application`, `usecase`, and `port` packages
- extra adapter layers before they are needed by real features

## Boundary Notes
- API DTOs must not be used as domain entities
- Domain objects must not import Spring framework classes
- Keep domain logic pure without Spring annotations

## Conventions
- Package naming follows `com.amanna.billingmanagement.<layer>`
- Constructor injection is preferred for API and adapter classes
- Domain layer is immutable first and invariant driven
- Keep flow direct: controller -> domain -> response DTO
- Keep exception handling minimal unless a real need appears

