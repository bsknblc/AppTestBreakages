package com.WebApps.Benchmark.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_TEST_CASE")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "NAME")
    private String testCaseName;

    @OneToMany(mappedBy = "testCase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestCaseVersion> testCaseVersions = new ArrayList<>();

    @ManyToOne
    @NotNull
    @JoinColumn(name = "test_suite_id", nullable = false)
    private TestSuite testSuite;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    public TestCase() {}

    public TestCase(String testCaseName, TestSuite testSuite, String description) {
        this.testCaseName = testCaseName;
        this.testSuite = testSuite;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public List<TestCaseVersion> getTestCaseVersions() {
        return testCaseVersions;
    }

    public void setTestCaseVersions(List<TestCaseVersion> testCaseVersions) {
        this.testCaseVersions = testCaseVersions;
    }

    public TestSuite getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(TestSuite testSuite) {
        this.testSuite = testSuite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
