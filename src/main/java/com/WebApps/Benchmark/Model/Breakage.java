package com.WebApps.Benchmark.Model;

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
    private List<Repair> repairs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "breakage_reason_id", nullable = false)
    private BreakageReason breakageReason;

    @ManyToOne
    @JoinColumn(name = "locathing_method_id", nullable = false)
    private LocatingMethod locatingMethod;

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

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public String getTaxonomyDescription() {
        return taxonomyDescription;
    }

    public void setTaxonomyDescription(String taxonomyDescription) {
        this.taxonomyDescription = taxonomyDescription;
    }

    public LocatingMethod getLocatingMethod() {
        return locatingMethod;
    }

    public void setLocatingMethod(LocatingMethod locatingMethod) {
        this.locatingMethod = locatingMethod;
    }

    public BreakageReason getBreakageReason() {
        return breakageReason;
    }

    public void setBreakageReason(BreakageReason breakageReason) {
        this.breakageReason = breakageReason;
    }
}
