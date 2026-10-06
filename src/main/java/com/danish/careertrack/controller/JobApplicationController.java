package com.danish.careertrack.controller;

import com.danish.careertrack.model.ApplicationStatus;
import com.danish.careertrack.model.JobApplication;
import com.danish.careertrack.service.JobApplicationService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class JobApplicationController {

    private final JobApplicationService service;


    public JobApplicationController(
            JobApplicationService service
    ) {
        this.service = service;
    }


    @GetMapping
    public Map<String, String> home() {

        return Map.of(
                "application",
                "CareerTrack",
                "status",
                "running",
                "version",
                "1.0.0"
        );
    }


    @GetMapping("/applications")
    public List<JobApplication> getApplications(

            @RequestParam(
                    required = false
            )
            ApplicationStatus status,

            @RequestParam(
                    required = false
            )
            String search

    ) {

        return service.getApplications(
                status,
                search
        );
    }


    @GetMapping("/applications/{id}")
    public JobApplication getApplication(
            @PathVariable Long id
    ) {

        return service.getApplication(id);
    }


    @PostMapping("/applications")
    @ResponseStatus(HttpStatus.CREATED)
    public JobApplication createApplication(

            @Valid
            @RequestBody
            JobApplication application

    ) {

        return service.createApplication(
                application
        );
    }


    @PutMapping("/applications/{id}")
    public JobApplication updateApplication(

            @PathVariable Long id,

            @Valid
            @RequestBody
            JobApplication application

    ) {

        return service.updateApplication(
                id,
                application
        );
    }


    @PatchMapping(
            "/applications/{id}/status"
    )
    public JobApplication updateStatus(

            @PathVariable Long id,

            @RequestParam
            ApplicationStatus status

    ) {

        return service.updateStatus(
                id,
                status
        );
    }


    @DeleteMapping("/applications/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplication(
            @PathVariable Long id
    ) {

        service.deleteApplication(id);
    }


    @GetMapping("/analytics/summary")
    public Map<String, Object> getSummary() {

        return service.getSummary();
    }


    @GetMapping(
            "/analytics/status-breakdown"
    )
    public Map<String, Long> getStatusBreakdown() {

        return service.getStatusBreakdown();
    }
}
