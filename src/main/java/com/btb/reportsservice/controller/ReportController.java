package com.btb.reportsservice.controller;

import com.btb.reportsservice.service.ReportService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/reports")
public class ReportController {

    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/activity")
    public void getActivityReport(@RequestParam String email) {
        log.trace("GET /reports/activity email: {}", email);

        log.info("ImportantEvent: Generate activity report for user: {}", email);
        reportService.getActivityReport(email);
    }

    @GetMapping("/transaction")
    public void getTransactionReport(@RequestParam String email) {
        log.trace("GET /reports/transaction email: {}", email);

        log.info("ImportantEvent: Generate transaction report for user: {}", email);
        reportService.getTransactionReport(email);
    }

}
