package com.btb.reportsservice.controller;

import com.btb.reportsservice.dto.ActivityReportDTO;
import com.btb.reportsservice.dto.TransactionReportDTO;
import com.btb.reportsservice.service.ReportService;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JRException;
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
    public void getActivityReport(@RequestParam String email, @RequestParam ActivityReportDTO activityReportDTO) throws JRException {
        log.trace("GET /reports/activity email: {}", email);

        log.info("ImportantEvent: Generate activity report for user: {}", email);
        reportService.getActivityReport(email, activityReportDTO);
    }

    @GetMapping("/transaction")
    public void getTransactionReport(@RequestParam String email, @RequestParam TransactionReportDTO transactionReportDTO) throws JRException {
        log.trace("GET /reports/transaction email: {}", email);

        log.info("ImportantEvent: Generate transaction report for user: {}", email);
        reportService.getTransactionReport(email, transactionReportDTO);
    }

}
