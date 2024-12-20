package com.atharva.SocialMediaAPI.repository;

import com.atharva.SocialMediaAPI.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepo extends JpaRepository<Report, String> {

}
