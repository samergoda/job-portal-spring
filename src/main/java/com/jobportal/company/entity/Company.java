package com.jobportal.company.entity;

import com.jobportal.common.entity.BaseEntity;
import com.jobportal.job.entity.Job;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COMPANIES")
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "Company.fetchCompaniesWithJobsByStatus", query =
                "SELECT DISTINCT c FROM Company c JOIN FETCH c.jobs j WHERE j.status = :status"),
        @NamedQuery(name = "Company.updateCompanyDetails",
                query =
                        """
                                UPDATE Company c SET
                                                            c.name = :name,
                                                            c.logo = :logo,
                                                            c.industry = :industry,
                                                            c.size = :size,
                                                            c.rating = :rating,
                                                            c.locations = :locations,
                                                            c.founded = :founded,
                                                            c.description = :description,
                                                            c.employees = :employees,
                                                            c.website = :website
                                                        WHERE c.id = :id
                        """
        )})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Company.fetchCompaniesWithJobsByStatusNative",
                query = "SELECT DISTINCT c.* FROM companies c JOIN jobs j ON c.id = j.company_id WHERE j.status = ?",
                resultClass = Company.class)
})
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "LOGO", length = 500)
    private String logo;

    @Column(name = "INDUSTRY", nullable = false, length = 100)
    private String industry;

    @Column(name = "SIZE", nullable = false, length = 50)
    private String size;

    @Column(name = "RATING", nullable = false, precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(name = "LOCATIONS", length = 1000)
    private String locations;

    @Column(name = "FOUNDED", nullable = false)
    private Integer founded;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "EMPLOYEES")
    private Integer employees;

    @Column(name = "WEBSITE", length = 500)
    private String website;

    /*
    For @ManyToOne/@OneToOne → Hibernate always uses a JOIN in the main query.
    For @OneToMany  → Hibernate's default strategy is not to JOIN by default.
    Instead, it often uses a secondary select (one query per collection) unless configured otherwise.
     */
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 10)
    private List<Job> jobs = new ArrayList<>();

}