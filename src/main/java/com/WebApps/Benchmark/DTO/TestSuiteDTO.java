package com.WebApps.Benchmark.DTO;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteDTO {

    private int id;
    private String testSuiteName;
    private String url;
    private List<TestCaseDTO> testCases = new ArrayList<>();
    private int applicationId;

    public TestSuiteDTO() {}

    public TestSuiteDTO(int id, String testSuiteName, String url, int applicationId, List<TestCaseDTO> testCases) {
        this.id = id;
        this.testSuiteName = testSuiteName;
        this.url = url;
        this.applicationId = applicationId;
        this.testCases = testCases;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestSuiteName() {
        return testSuiteName;
    }

    public void setTestSuiteName(String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TestCaseDTO> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCaseDTO> testCases) {
        this.testCases = testCases;
    }

    public int getApplicationId() {
        return applicationId;
    }
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }
}
