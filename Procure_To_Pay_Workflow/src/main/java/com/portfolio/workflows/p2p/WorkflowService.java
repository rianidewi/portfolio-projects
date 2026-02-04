package com.portfolio.workflows.p2p;

import java.util.UUID;

public class WorkflowService {
    private final WorkflowRepository repository = new WorkflowRepository();

    public WorkflowRequest createDraft(String owner, String summary, double amount, String currency) {
        WorkflowRequest request = new WorkflowRequest(UUID.randomUUID().toString());
        request.setOwner(owner);
        request.setSummary(summary);
        request.setTotalAmount(amount);
        request.setCurrency(currency);
        request.addAudit("Created draft request by " + owner);
        repository.save(request);
        return request;
    }

    public WorkflowRequest advance(String id, WorkflowStage nextStage, String actor) {
        WorkflowRequest request = repository.find(id);
        if (request == null) {
            throw new IllegalArgumentException("Request not found");
        }
        request.setStage(nextStage);
        request.addAudit("Moved to " + nextStage + " by " + actor);
        repository.save(request);
        return request;
    }

    public WorkflowRequest reject(String id, String actor, String reason) {
        WorkflowRequest request = repository.find(id);
        if (request == null) {
            throw new IllegalArgumentException("Request not found");
        }
        request.setStage(WorkflowStage.REJECTED);
        request.addAudit("Rejected by " + actor + ": " + reason);
        repository.save(request);
        return request;
    }

    public WorkflowRepository getRepository() {
        return repository;
    }
}
