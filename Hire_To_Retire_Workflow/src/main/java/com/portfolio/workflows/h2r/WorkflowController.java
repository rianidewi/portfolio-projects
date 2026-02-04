package com.portfolio.workflows.h2r;

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

    @GetMapping("/workflows/hire-to-retire-workflow")
    public String list(Model model) {
        List<WorkflowRequest> requests = service.getRepository().list();
        model.addAttribute("requests", requests);
        model.addAttribute("title", "Hire-to-Retire Workflow");
        return "workflows/list";
    }

    @GetMapping("/workflows/hire-to-retire-workflow/{id}")
    public String detail(@PathVariable String id, Model model) {
        WorkflowRequest request = service.getRepository().find(id);
        model.addAttribute("request", request);
        model.addAttribute("title", "Hire-to-Retire Workflow");
        return "workflows/detail";
    }

    @PostMapping("/workflows/hire-to-retire-workflow/advance")
    public String advance(@RequestParam String id, @RequestParam String next, @RequestParam String actor) {
        service.advance(id, WorkflowStage.valueOf(next), actor);
        return "redirect:/workflows/hire-to-retire-workflow/" + id;
    }

    @PostMapping("/workflows/hire-to-retire-workflow/reject")
    public String reject(@RequestParam String id, @RequestParam String actor, @RequestParam String reason) {
        service.reject(id, actor, reason);
        return "redirect:/workflows/hire-to-retire-workflow/" + id;
    }
}
