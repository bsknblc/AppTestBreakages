package com.WebApps.Benchmark.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_BREAKAGE")
public class Breakage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "app_release_id", nullable = false)
    @NotNull
    private AppRelease appRelease;

    @ManyToOne
    @JoinColumn(name = "test_case_version_id", nullable = false)
    @NotNull
    private TestCaseVersion testCaseVersion;

    @OneToMany(mappedBy = "breakage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RepairALineOfCode> repairALineOfCodes = new ArrayList<>();

    @NotNull
    @Column(name = "TAXONOMY_DESCRIPTION")
    private String taxonomyDescription;

    public Breakage(){}

    public Breakage(AppRelease appRelease, TestCaseVersion testCaseVersion, String taxonomyDescription) {
        this.appRelease = appRelease;
        this.testCaseVersion = testCaseVersion;
        this.taxonomyDescription = taxonomyDescription;
    }

    public int getId() {
        return id;
    }

    public AppRelease getAppRelease() {
        return appRelease;
    }

    public void setAppRelease(AppRelease appRelease) {
        this.appRelease = appRelease;
    }

    public TestCaseVersion getTestCaseVersion() {
        return testCaseVersion;
    }

    public void setTestCaseVersion(TestCaseVersion testCaseVersion) {
        this.testCaseVersion = testCaseVersion;
    }

    public List<RepairALineOfCode> getRepairALineOfCodes() {
        return repairALineOfCodes;
    }

    public void setRepairALineOfCodes(List<RepairALineOfCode> repairALineOfCodes) {
        this.repairALineOfCodes = repairALineOfCodes;
    }

    public String getTaxonomyDescription() {
        return taxonomyDescription;
    }

    public void setTaxonomyDescription(String taxonomyDescription) {
        this.taxonomyDescription = taxonomyDescription;
    }
}
