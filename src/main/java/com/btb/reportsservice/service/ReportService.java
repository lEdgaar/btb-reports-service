package com.btb.reportsservice.service;

import com.btb.reportsservice.dto.ActivityReportDTO;
import com.btb.reportsservice.dto.TransactionReportDTO;
import net.sf.jasperreports.engine.JRException;

public interface ReportService {

    void getActivityReport(String email, ActivityReportDTO activityReportDTO) throws JRException;

    void getTransactionReport(String email, TransactionReportDTO transactionReportDTO) throws JRException;

}
