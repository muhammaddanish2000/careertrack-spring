package com.danish.careertrack;

import com.danish.careertrack.model.ApplicationStatus;
import com.danish.careertrack.model.JobApplication;
import com.danish.careertrack.repository.JobApplicationRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class CareerTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareerTrackApplication.class, args);
    }

    @Bean
    CommandLineRunner seedDatabase(JobApplicationRepository repository) {

        return args -> {

            if (repository.count() > 0) {
                return;
            }

            JobApplication application1 = new JobApplication();

            application1.setCompany("TechNova Solutions");
            application1.setRole("Data Engineer");
            application1.setLocation("Singapore");
            application1.setSource("LinkedIn");
            application1.setStatus(ApplicationStatus.APPLIED);
            application1.setAppliedDate(LocalDate.now().minusDays(4));
            application1.setNotes(
                    "Applied for Data Engineer position focusing on cloud data pipelines."
            );

            JobApplication application2 = new JobApplication();

            application2.setCompany("CloudWorks Asia");
            application2.setRole("Cloud Engineer");
            application2.setLocation("Kuala Lumpur");
            application2.setSource("Company Website");
            application2.setStatus(ApplicationStatus.INTERVIEW);
            application2.setAppliedDate(LocalDate.now().minusDays(8));
            application2.setNotes(
                    "First technical interview scheduled."
            );

            JobApplication application3 = new JobApplication();

            application3.setCompany("Digital Analytics Lab");
            application3.setRole("Junior Data Analyst");
            application3.setLocation("Johor Bahru");
            application3.setSource("JobStreet");
            application3.setStatus(ApplicationStatus.OFFER);
            application3.setAppliedDate(LocalDate.now().minusDays(15));
            application3.setNotes(
                    "Received preliminary offer and waiting for final package."
            );

            repository.save(application1);
            repository.save(application2);
            repository.save(application3);
        };
    }
}
