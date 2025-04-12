package com.WebApps.Benchmark.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_TEST_CASE_VERSION")
public class TestCaseVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "NAME")
    private String testCaseVersionName;

    @OneToMany(mappedBy = "testCaseVersion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineOfCode> lineOfCodes = new ArrayList<>();

    @OneToMany(mappedBy = "testCaseVersion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Breakage> breakages = new ArrayList<>();

    @ManyToOne
    @NotNull
    @JoinColumn(name = "test_case_id", nullable = false)
    private TestCase testCase;

    public TestCaseVersion(){}

    public TestCaseVersion(String testCaseVersionName, TestCase testCase) {
        this.testCaseVersionName = testCaseVersionName;
        this.testCase = testCase;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public List<Breakage> getBreakages() {
        return breakages;
    }

    public void setBreakages(List<Breakage> breakages) {
        this.breakages = breakages;
    }

    public List<LineOfCode> getLineOfCodes() {
        return lineOfCodes;
    }

    public void setLineOfCodes(List<LineOfCode> lineOfCodes) {
        this.lineOfCodes = lineOfCodes;
    }

    public String getTestCaseVersionName() {
        return testCaseVersionName;
    }

    public void setTestCaseVersionName(String testCaseVersionName) {
        this.testCaseVersionName = testCaseVersionName;
    }

    public int getId() {
        return id;
    }

}
