package com.jobportal.company.repository;

import com.jobportal.company.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    /**
     * Fetch all companies that have jobs with the given status (JPQL with JOIN FETCH).
     */
    @Query("SELECT DISTINCT c FROM Company c JOIN FETCH c.jobs j WHERE j.status = :status")
    List<Company> findAllWithJobsByStatus(@Param("status") String status);

    /**
     * Paginated version - fetch companies with jobs by status.
     * Note: JOIN FETCH cannot be used with pagination, so we use regular JOIN here.
     */
    @Query(value = "SELECT DISTINCT c FROM Company c JOIN c.jobs j WHERE j.status = :status",
            countQuery = "SELECT COUNT(DISTINCT c) FROM Company c JOIN c.jobs j WHERE j.status = :status")
    Page<Company> findCompaniesWithJobsByStatus(@Param("status") String status, Pageable pageable);

    /**
     * Update company details by ID.
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Company c SET c.name = :name, c.logo = :logo, c.industry = :industry, " +
            "c.size = :size, c.rating = :rating, c.locations = :locations, c.founded = :founded, " +
            "c.description = :description, c.employees = :employees, c.website = :website " +
            "WHERE c.id = :id")
    int updateCompanyDetails(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("logo") String logo,
            @Param("industry") String industry,
            @Param("size") String size,
            @Param("rating") BigDecimal rating,
            @Param("locations") String locations,
            @Param("founded") Integer founded,
            @Param("description") String description,
            @Param("employees") Integer employees,
            @Param("website") String website
    );

}
