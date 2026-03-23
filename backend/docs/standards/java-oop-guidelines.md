# Java and OOP Guidelines

## Core Principles
- Use encapsulation and immutable value objects by default
- Keep classes focused on one responsibility
- Prefer composition over inheritance for business behavior

## Design Rules
- Validate external input at API boundary
- Enforce business invariants in domain model
- Keep persistence and transport concerns outside domain

## Coding Conventions
- Package naming in lower case by layer and feature
- Descriptive class names with domain terms
- Avoid static mutable state
- Keep methods small and intention revealing

## Testing Expectations
- Unit test domain logic and validation first
- Add integration tests for API behavior and wiring
- Cover negative scenarios for validation and state rules

