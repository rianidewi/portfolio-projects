package com.portfolio.demo.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WorkflowDemoController {
    private static final Map<String, WorkflowDemo> DEMOS = new LinkedHashMap<String, WorkflowDemo>();

    static {
        DEMOS.put(
            "procure-to-pay-workflow",
            new WorkflowDemo(
                "Procure-to-Pay (P2P)",
                "Requisition, PO, goods receipt, invoice validation, and payment approval.",
                Arrays.asList("Purchase Requisition", "PO Approved", "Goods Receipt", "Invoice Matched", "Payment Released")
            )
        );
        DEMOS.put(
            "order-to-cash-workflow",
            new WorkflowDemo(
                "Order-to-Cash (O2C)",
                "Sales order processing through delivery, invoicing, and collection.",
                Arrays.asList("Sales Order", "Pick & Pack", "Delivery", "Invoice Issued", "Payment Received")
            )
        );
        DEMOS.put(
            "record-to-report-workflow",
            new WorkflowDemo(
                "Record-to-Report (R2R)",
                "Period close workflow for journals, approvals, and reporting.",
                Arrays.asList("Journal Entry", "Manager Review", "Posting", "Period Close", "Financial Report")
            )
        );
        DEMOS.put(
            "hire-to-retire-workflow",
            new WorkflowDemo(
                "Hire-to-Retire (H2R)",
                "HR lifecycle from hiring to offboarding with approvals.",
                Arrays.asList("Hiring Approved", "Offer Accepted", "Onboarding", "Payroll Active", "Offboarding Complete")
            )
        );
        DEMOS.put(
            "inventory-replenishment-workflow",
            new WorkflowDemo(
                "Inventory Replenishment",
                "Demand signal to purchase order, goods receipt, and stock update.",
                Arrays.asList("Demand Signal", "Reorder Planned", "PO Sent", "Goods Receipt", "Stock Updated")
            )
        );
        DEMOS.put(
            "returns-rma-workflow",
            new WorkflowDemo(
                "Returns / RMA",
                "Return request approvals, receiving, inspection, and credit note.",
                Arrays.asList("Return Requested", "Approved", "Item Received", "Inspection Complete", "Credit Issued")
            )
        );
    }

    @GetMapping("/demo/workflows/{slug}")
    public String workflowDemo(@PathVariable String slug, Model model) {
        WorkflowDemo demo = DEMOS.get(slug);
        if (demo == null) {
            return "redirect:/demo";
        }
        model.addAttribute("slug", slug);
        model.addAttribute("title", demo.title);
        model.addAttribute("summary", demo.summary);
        model.addAttribute("stages", demo.stages);
        return "workflows/demo";
    }

    private static class WorkflowDemo {
        private final String title;
        private final String summary;
        private final List<String> stages;

        private WorkflowDemo(String title, String summary, List<String> stages) {
            this.title = title;
            this.summary = summary;
            this.stages = stages;
        }
    }
}
