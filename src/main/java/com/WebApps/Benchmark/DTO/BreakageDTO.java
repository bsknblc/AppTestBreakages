package com.WebApps.Benchmark.DTO;

import java.util.ArrayList;
import java.util.List;

public class BreakageDTO {

    private int id;
    private int appReleaseId;
    private int testCaseVersionId;
    private int breakageReasonId;
    private int locatingMethodId;
    private List<RepairDTO> repairs = new ArrayList<>();
    private String taxonomyDescription;

    public BreakageDTO() {}

    public BreakageDTO(int id, int appReleaseId, int testCaseVersionId, int breakageReasonId, int locatingMethodId, List<RepairDTO> repairs, String taxonomyDescription) {
        this.id = id;
        this.appReleaseId = appReleaseId;
        this.testCaseVersionId = testCaseVersionId;
        this.breakageReasonId = breakageReasonId;
        this.locatingMethodId = locatingMethodId;
        this.repairs = repairs;
        this.taxonomyDescription = taxonomyDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppReleaseId() {
        return appReleaseId;
    }

    public void setAppReleaseId(int appReleaseId) {
        this.appReleaseId = appReleaseId;
    }

    public int getTestCaseVersionId() {
        return testCaseVersionId;
    }

    public void setTestCaseVersionId(int testCaseVersionId) {
        this.testCaseVersionId = testCaseVersionId;
    }

    public List<RepairDTO> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<RepairDTO> repairs) {
        this.repairs = repairs;
    }

    public String getTaxonomyDescription() {
        return taxonomyDescription;
    }

    public void setTaxonomyDescription(String taxonomyDescription) {
        this.taxonomyDescription = taxonomyDescription;
    }

    public int getLocatingMethodId() {
        return locatingMethodId;
    }

    public void setLocatingMethodId(int locatingMethodId) {
        this.locatingMethodId = locatingMethodId;
    }

    public int getBreakageReasonId() {
        return breakageReasonId;
    }

    public void setBreakageReasonId(int breakageReasonId) {
        this.breakageReasonId = breakageReasonId;
    }
}
