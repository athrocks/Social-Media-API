package com.atharva.SocialMediaAPI.controller;

import com.atharva.SocialMediaAPI.model.Report;
import com.atharva.SocialMediaAPI.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/")
    public Report createReport(@RequestBody Report report) {
        return reportService.createReport(report);
    }

    @GetMapping("/")
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{reportId}")
    public Report getReportById(@PathVariable String reportId) {
        return reportService.getReportById(reportId);
    }

    @PutMapping("/{reportId}")
    public Report updateReport(@PathVariable String reportId, @RequestBody Report newReport) {
        return reportService.updateReport(reportId,newReport);
    }

    @DeleteMapping("/{reportId}")
    public void deleteReport(@PathVariable String reportId) {
        reportService.deleteReport(reportId);
    }

    // Retrieves all reports created by a specific user.
    @GetMapping("/users/{userId}")
    public List<Report> getReportsByUserId(@PathVariable String userId) {
        return reportService.getReportsByUserId(userId);
    }


    /*
    If you're reporting a Post, idTarget will store the postID of the reported post.
    If you're reporting a Comment, idTarget will store the commentID of the reported comment.
    If you're reporting a User, idTarget will store the userID of the reported user.
    */

    /*
    typeReport:
                1 = Post
                2 = Comment
                3 = User
     */


}
