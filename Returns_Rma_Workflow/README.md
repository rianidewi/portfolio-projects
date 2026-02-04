# Returns / RMA Workflow

This is a fullstack workflow sample that mirrors a common enterprise process. The focus is on state transitions, approvals, and audit-friendly data structures.

## Workflow stages
REQUESTED, APPROVED, ITEM_RECEIVED, CREDIT_ISSUED, CLOSED, REJECTED

## Key capabilities
- Step validation with guardrails
- Simple in-memory repository for demo data
- Controller endpoints to list, view, and advance a request
- Views for list, detail, and approval actions
