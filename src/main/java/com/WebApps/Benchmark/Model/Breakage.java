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

    @ManyToMany
    @JoinTable(
            name = "BREAKAGE_EXPLANATION",
            joinColumns = @JoinColumn(name = "breakage_id"),
            inverseJoinColumns = @JoinColumn(name = "breakage_explanation_id")
    )
    private List<BreakageExplanation> breakageExplanations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "breakage_reason_id", nullable = false)
    @NotNull
    private BreakageReason breakageReason;

    @ManyToOne
    @JoinColumn(name = "locathing_method_id", nullable = false)
    private LocatingMethod locatingMethod;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "LINE")
    private String line;

    public Breakage(){}

    public Breakage(AppRelease appRelease, TestCaseVersion testCaseVersion, BreakageReason breakageReason, LocatingMethod locatingMethod, String description, String line, BreakageExplanation breakageExplanation) {
        this.appRelease = appRelease;
        this.testCaseVersion = testCaseVersion;
        this.breakageReason = breakageReason;
        this.locatingMethod = locatingMethod;
        this.description = description;
        this.line = line;
        this.breakageExplanations.add(breakageExplanation);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public List<BreakageExplanation> getBreakageExplanations() {
        return breakageExplanations;
    }

    public void setBreakageExplanations(List<BreakageExplanation> breakageExplanations) {
        this.breakageExplanations = breakageExplanations;
    }
}
