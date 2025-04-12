package com.WebApps.Benchmark.DTO;

import java.util.List;
public class TestCaseDTO {

    private int id;
    private String testCaseName;
    private String description;
    private int testSuiteId; // Reference to TestSuite by ID
    private List<TestCaseVersionDTO> testCaseVersions;

    public TestCaseDTO() {}

    public TestCaseDTO(int id, String testCaseName, String description, int testSuiteId, List<TestCaseVersionDTO> testCaseVersions) {
        this.id = id;
        this.testCaseName = testCaseName;
        this.description = description;
        this.testSuiteId = testSuiteId;
        this.testCaseVersions = testCaseVersions;
    }

    // Getters and Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTestSuiteId() {
        return testSuiteId;
    }

    public void setTestSuiteId(int testSuiteId) {
        this.testSuiteId = testSuiteId;
    }

    public List<TestCaseVersionDTO> getTestCaseVersions() {
        return testCaseVersions;
    }

    public void setTestCaseVersions(List<TestCaseVersionDTO> testCaseVersions) {
        this.testCaseVersions = testCaseVersions;
    }
}
