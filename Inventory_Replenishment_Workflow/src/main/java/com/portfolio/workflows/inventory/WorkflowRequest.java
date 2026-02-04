package com.portfolio.workflows.inventory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkflowRequest {
    private final String id;
    private String owner;
    private String summary;
    private double totalAmount;
    private String currency;
    private WorkflowStage stage;
    private LocalDateTime createdAt;
    private final List<String> auditTrail = new ArrayList<>();

    public WorkflowRequest(String id) {
        this.id = id;
        this.stage = WorkflowStage.DEMAND_SIGNAL;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public WorkflowStage getStage() { return stage; }
    public void setStage(WorkflowStage stage) { this.stage = stage; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<String> getAuditTrail() { return auditTrail; }

    public void addAudit(String entry) {
        auditTrail.add(LocalDateTime.now() + " - " + entry);
    }
}
