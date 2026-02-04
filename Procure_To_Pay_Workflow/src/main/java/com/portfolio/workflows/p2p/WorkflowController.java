package com.portfolio.workflows.p2p;

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

    @GetMapping("/workflows/procure-to-pay-workflow")
    public String list(Model model) {
        List<WorkflowRequest> requests = service.getRepository().list();
        model.addAttribute("requests", requests);
        model.addAttribute("title", "Procure-to-Pay Workflow");
        return "workflows/list";
    }

    @GetMapping("/workflows/procure-to-pay-workflow/{id}")
    public String detail(@PathVariable String id, Model model) {
        WorkflowRequest request = service.getRepository().find(id);
        model.addAttribute("request", request);
        model.addAttribute("title", "Procure-to-Pay Workflow");
        return "workflows/detail";
    }

    @PostMapping("/workflows/procure-to-pay-workflow/advance")
    public String advance(@RequestParam String id, @RequestParam String next, @RequestParam String actor) {
        service.advance(id, WorkflowStage.valueOf(next), actor);
        return "redirect:/workflows/procure-to-pay-workflow/" + id;
    }

    @PostMapping("/workflows/procure-to-pay-workflow/reject")
    public String reject(@RequestParam String id, @RequestParam String actor, @RequestParam String reason) {
        service.reject(id, actor, reason);
        return "redirect:/workflows/procure-to-pay-workflow/" + id;
    }
}
