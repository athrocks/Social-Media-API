package com.atharva.SocialMediaAPI.service;

import com.atharva.SocialMediaAPI.model.AppUser;
import com.atharva.SocialMediaAPI.model.Report;
import com.atharva.SocialMediaAPI.repository.ReportRepo;
import com.atharva.SocialMediaAPI.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReportService {

    private final ReportRepo reportRepo;
    private final UserRepo userRepo;
    @Autowired
    public ReportService(ReportRepo reportRepo,UserRepo userRepo) {
        this.reportRepo = reportRepo;
        this.userRepo = userRepo;
    }

    public Report createReport(Report report) {
        log.info("Report is: {}", report);

        Optional<AppUser> user = userRepo.findById(report.getUserReport().getUserID());

        if(user.isEmpty()) {
            log.error("User not found");
            return null;
        }

        report.setUserReport(user.get());

        try {
            log.info("Report is being created");
            return reportRepo.save(report);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public List<Report> getAllReports() {
        return reportRepo.findAll();
    }


    public Report getReportById(String reportId) {
        Optional<Report> report = reportRepo.findById(reportId);
        if(report.isEmpty()) {
            log.error("Report not found");
            return null;
        }
        return report.get();
    }

    public Report updateReport(String reportId, Report newReport) {
        Optional<Report> reportOptional = reportRepo.findById(reportId);
        if(reportOptional.isEmpty()) {
            log.error("Report not found with id: {}", reportId);
            return null;
        }

        Report report = reportOptional.get();

        try {
            report =  Report.builder()
                    .reportID(reportId)
                    .content(newReport.getContent())
                    .typeReport(newReport.getTypeReport())
                    .idTarget(newReport.getIdTarget())
                    .userReport(newReport.getUserReport())
                    .build();

            log.info("Report updated to : {}", report);
            return  reportRepo.save(report);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public void deleteReport(String reportId) {
        reportRepo.deleteById(reportId);
    }

    public List<Report> getReportsByUserId(String userId) {
        List<Report> reportList = reportRepo.findAll();
        List<Report> userReportList = new ArrayList<>();

        try {
            for(Report report : reportList) {
                if(report.getUserReport().getUserID().equals(userId)) {
                    userReportList.add(report);
                }
            }
            log.info("Report list is: {}", userReportList);
            return userReportList;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
