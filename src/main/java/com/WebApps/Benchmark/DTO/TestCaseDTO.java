package com.WebApps.Benchmark.DTO;

import com.WebApps.Benchmark.Model.TestCaseVersion;
import com.WebApps.Benchmark.Model.TestSuite;

import java.util.ArrayList;
import java.util.List;

public class TestCaseDTO {
    private int id;
    private String testCaseName;
    private List<TestCaseVersion> testCaseVersions = new ArrayList<>();
    private TestSuite testSuite;
    private String description;

    public TestCaseDTO(int id, String testCaseName, List<TestCaseVersion> testCaseVersions, TestSuite testSuite, String description) {
        this.id = id;
        this.testCaseName = testCaseName;
        this.testCaseVersions = testCaseVersions;
        this.testSuite = testSuite;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
