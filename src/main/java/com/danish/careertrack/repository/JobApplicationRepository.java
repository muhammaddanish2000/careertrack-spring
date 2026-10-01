package com.danish.careertrack.repository;

import com.danish.careertrack.model.ApplicationStatus;
import com.danish.careertrack.model.JobApplication;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByStatus(ApplicationStatus status);

    List<JobApplication> findByCompanyContainingIgnoreCase(String company);

    List<JobApplication> findByRoleContainingIgnoreCase(String role);

    long countByStatus(ApplicationStatus status);
}
