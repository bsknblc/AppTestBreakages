package com.WebApps.Benchmark.DTO;


import java.util.ArrayList;
import java.util.List;


public class TestCaseVersionDTO {

    private int id;
    private String testCaseVersionName;
    private int testCaseId;
    private List<LineOfCodeDTO> lineOfCodes = new ArrayList<>();
    private List<BreakageDTO> breakages = new ArrayList<>();

    public TestCaseVersionDTO() {}

    public TestCaseVersionDTO(int id, String testCaseVersionName, int testCaseId, List<LineOfCodeDTO> lineOfCodes, List<BreakageDTO> breakages) {
        this.id = id;
        this.testCaseVersionName = testCaseVersionName;
        this.testCaseId = testCaseId;
        this.lineOfCodes = lineOfCodes;
        this.breakages = breakages;
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

    public List<LineOfCodeDTO> getLineOfCodes() {
        return lineOfCodes;
    }

    public void setLineOfCodes(List<LineOfCodeDTO> lineOfCodes) {
        this.lineOfCodes = lineOfCodes;
    }

    public List<BreakageDTO> getBreakages() {
        return breakages;
    }

    public void setBreakages(List<BreakageDTO> breakages) {
        this.breakages = breakages;
    }
}
