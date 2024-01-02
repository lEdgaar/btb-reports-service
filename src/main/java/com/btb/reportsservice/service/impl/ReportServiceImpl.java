package com.btb.reportsservice.service.impl;

import com.btb.reportsservice.dto.ActivityReportDTO;
import com.btb.reportsservice.dto.TransactionReportDTO;
import com.btb.reportsservice.service.ReportService;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public void getActivityReport(String email, ActivityReportDTO activityReportDTO) throws JRException {
        log.info("Event: Get activity report");

        InputStream inputStream = getClass().getResourceAsStream("/reports/history_event.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRSaver.saveObject(jasperReport, "reports/history_event.jasper");
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("id", activityReportDTO.getId());
        parameters.put("userId", activityReportDTO.getUserId());
        parameters.put("eventType", activityReportDTO.getEventType());
        parameters.put("atDatetime", activityReportDTO.getAtDatetime());
        parameters.put("description", activityReportDTO.getDescription());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
        getPdf(jasperPrint);
    }

    @Override
    public void getTransactionReport(String email, TransactionReportDTO transactionReportDTO) throws JRException {
        log.info("Event: Get transaction report");

        InputStream inputStream = getClass().getResourceAsStream("/reports/transactions.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRSaver.saveObject(jasperReport, "reports/transactions.jasper");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("id", transactionReportDTO.getId());
        parameters.put("createdAt", transactionReportDTO.getCreatedAt());
        parameters.put("transactionType", transactionReportDTO.getTransactionType());
        parameters.put("quantity", transactionReportDTO.getQuantity());
        parameters.put("asset", transactionReportDTO.getAsset());
        parameters.put("priceUnit", transactionReportDTO.getPriceUnit());
        parameters.put("user", transactionReportDTO.getUser());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
        getPdf(jasperPrint);
    }

    private void getPdf(JasperPrint jasperPrint) throws JRException {
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(
                new SimpleOutputStreamExporterOutput("btb-report.pdf"));

        SimplePdfReportConfiguration reportConfig
                = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig
                = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor("BTB");
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);

        exporter.exportReport();
    }
}
