package com.danish.careertrack.service;

import com.danish.careertrack.model.ApplicationStatus;
import com.danish.careertrack.model.JobApplication;
import com.danish.careertrack.repository.JobApplicationRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;


    public JobApplicationService(
            JobApplicationRepository repository
    ) {
        this.repository = repository;
    }


    public List<JobApplication> getApplications(
            ApplicationStatus status,
            String search
    ) {

        List<JobApplication> applications;

        if (status != null) {
            applications = repository.findByStatus(status);
        } else {
            applications = repository.findAll();
        }

        if (search != null && !search.isBlank()) {

            String keyword = search.toLowerCase();

            applications = applications.stream()
                    .filter(application ->

                            containsIgnoreCase(
                                    application.getCompany(),
                                    keyword
                            )

                            ||

                            containsIgnoreCase(
                                    application.getRole(),
                                    keyword
                            )

                            ||

                            containsIgnoreCase(
                                    application.getLocation(),
                                    keyword
                            )

                            ||

                            containsIgnoreCase(
                                    application.getSource(),
                                    keyword
                            )
                    )
                    .collect(Collectors.toList());
        }

        applications.sort(
                Comparator.comparing(
                        JobApplication::getUpdatedAt,
                        Comparator.nullsLast(
                                Comparator.reverseOrder()
                        )
                )
        );

        return applications;
    }


    private boolean containsIgnoreCase(
            String value,
            String keyword
    ) {

        return value != null &&
                value.toLowerCase().contains(keyword);
    }


    public JobApplication getApplication(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Application not found."
                        )
                );
    }


    public JobApplication createApplication(
            JobApplication application
    ) {

        application.setId(null);

        return repository.save(application);
    }


    public JobApplication updateApplication(
            Long id,
            JobApplication request
    ) {

        JobApplication application =
                getApplication(id);

        application.setCompany(
                request.getCompany()
        );

        application.setRole(
                request.getRole()
        );

        application.setLocation(
                request.getLocation()
        );

        application.setSource(
                request.getSource()
        );

        application.setStatus(
                request.getStatus()
        );

        application.setAppliedDate(
                request.getAppliedDate()
        );

        application.setNotes(
                request.getNotes()
        );

        return repository.save(application);
    }


    public JobApplication updateStatus(
            Long id,
            ApplicationStatus status
    ) {

        JobApplication application =
                getApplication(id);

        application.setStatus(status);

        return repository.save(application);
    }


    public void deleteApplication(Long id) {

        JobApplication application =
                getApplication(id);

        repository.delete(application);
    }


    public Map<String, Object> getSummary() {

        long total =
                repository.count();

        long saved =
                repository.countByStatus(
                        ApplicationStatus.SAVED
                );

        long applied =
                repository.countByStatus(
                        ApplicationStatus.APPLIED
                );

        long screening =
                repository.countByStatus(
                        ApplicationStatus.SCREENING
                );

        long interviews =
                repository.countByStatus(
                        ApplicationStatus.INTERVIEW
                )
                +
                repository.countByStatus(
                        ApplicationStatus.TECHNICAL_TEST
                )
                +
                repository.countByStatus(
                        ApplicationStatus.FINAL_INTERVIEW
                );

        long offers =
                repository.countByStatus(
                        ApplicationStatus.OFFER
                );

        long rejected =
                repository.countByStatus(
                        ApplicationStatus.REJECTED
                );

        long withdrawn =
                repository.countByStatus(
                        ApplicationStatus.WITHDRAWN
                );

        double interviewRate = 0;

        if (total > 0) {

            interviewRate =
                    ((double) interviews / total) * 100;
        }

        double offerRate = 0;

        if (total > 0) {

            offerRate =
                    ((double) offers / total) * 100;
        }

        Map<String, Object> summary =
                new LinkedHashMap<>();

        summary.put(
                "totalApplications",
                total
        );

        summary.put(
                "saved",
                saved
        );

        summary.put(
                "applied",
                applied
        );

        summary.put(
                "screening",
                screening
        );

        summary.put(
                "interviews",
                interviews
        );

        summary.put(
                "offers",
                offers
        );

        summary.put(
                "rejected",
                rejected
        );

        summary.put(
                "withdrawn",
                withdrawn
        );

        summary.put(
                "interviewRate",
                Math.round(interviewRate * 100.0) / 100.0
        );

        summary.put(
                "offerRate",
                Math.round(offerRate * 100.0) / 100.0
        );

        return summary;
    }


    public Map<String, Long> getStatusBreakdown() {

        Map<String, Long> result =
                new LinkedHashMap<>();

        for (
                ApplicationStatus status :
                ApplicationStatus.values()
        ) {

            result.put(
                    status.name(),
                    repository.countByStatus(status)
            );
        }

        return result;
    }
}
