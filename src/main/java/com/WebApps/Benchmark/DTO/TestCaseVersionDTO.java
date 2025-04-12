package com.WebApps.Benchmark.DTO;

import com.WebApps.Benchmark.Model.TestCase;


public class TestCaseVersionDTO {

    private int id;
    private String testCaseVersionName;
    private int testCaseId;

    public TestCaseVersionDTO() {}

    public TestCaseVersionDTO(int id, String testCaseVersionName, int testCaseId) {
        this.id = id;
        this.testCaseVersionName = testCaseVersionName;
        this.testCaseId = testCaseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestCaseVersionName() {
        return testCaseVersionName;
    }

    public void setTestCaseVersionName(String testCaseVersionName) {
        this.testCaseVersionName = testCaseVersionName;
    }

    public int getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }
}
