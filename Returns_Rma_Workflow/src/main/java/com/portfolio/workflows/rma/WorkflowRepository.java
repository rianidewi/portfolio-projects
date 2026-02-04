package com.portfolio.workflows.rma;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorkflowRepository {
    private final Map<String, WorkflowRequest> storage = new LinkedHashMap<>();

    public List<WorkflowRequest> list() {
        return new ArrayList<>(storage.values());
    }

    public WorkflowRequest find(String id) {
        return storage.get(id);
    }

    public void save(WorkflowRequest request) {
        storage.put(request.getId(), request);
    }
}
