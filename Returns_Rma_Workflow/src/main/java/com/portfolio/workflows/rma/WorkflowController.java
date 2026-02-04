package com.portfolio.workflows.rma;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkflowController {
    private final WorkflowService service = new WorkflowService();

    @GetMapping("/workflows/returns-rma-workflow")
    public String list(Model model) {
        List<WorkflowRequest> requests = service.getRepository().list();
        model.addAttribute("requests", requests);
        model.addAttribute("title", "Returns / RMA Workflow");
        return "workflows/list";
    }

    @GetMapping("/workflows/returns-rma-workflow/{id}")
    public String detail(@PathVariable String id, Model model) {
        WorkflowRequest request = service.getRepository().find(id);
        model.addAttribute("request", request);
        model.addAttribute("title", "Returns / RMA Workflow");
        return "workflows/detail";
    }

    @PostMapping("/workflows/returns-rma-workflow/advance")
    public String advance(@RequestParam String id, @RequestParam String next, @RequestParam String actor) {
        service.advance(id, WorkflowStage.valueOf(next), actor);
        return "redirect:/workflows/returns-rma-workflow/" + id;
    }

    @PostMapping("/workflows/returns-rma-workflow/reject")
    public String reject(@RequestParam String id, @RequestParam String actor, @RequestParam String reason) {
        service.reject(id, actor, reason);
        return "redirect:/workflows/returns-rma-workflow/" + id;
    }
}
