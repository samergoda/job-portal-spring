package com.jobportal.job.repository;

import com.jobportal.company.entity.Company;
import com.jobportal.job.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    Page<Job> findByCompany(Company company, Pageable pageable);
}
