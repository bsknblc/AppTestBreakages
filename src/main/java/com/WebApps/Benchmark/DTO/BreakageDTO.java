package com.WebApps.Benchmark.DTO;

import java.util.ArrayList;
import java.util.List;

public class BreakageDTO {

    private int id;
    private int appReleaseID;
    private int testCaseVersionID;
    private int breakageReasonID;
    private int locatingMethodID;
    private List<RepairDTO> repairs = new ArrayList<>();
    private String taxonomyDescription;

    public BreakageDTO() {}

    public BreakageDTO(int id, int appReleaseID, int testCaseVersionID, int breakageReasonID, int locatingMethodID, List<RepairDTO> repairs, String taxonomyDescription) {
        this.id = id;
        this.appReleaseID = appReleaseID;
        this.testCaseVersionID = testCaseVersionID;
        this.breakageReasonID = breakageReasonID;
        this.locatingMethodID = locatingMethodID;
        this.repairs = repairs;
        this.taxonomyDescription = taxonomyDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppRelease() {
        return appReleaseID;
    }

    public void setAppRelease(int appReleaseID) {
        this.appReleaseID = appReleaseID;
    }

    public int getTestCaseVersion() {
        return testCaseVersionID;
    }

    public void setTestCaseVersion(int testCaseVersionID) {
        this.testCaseVersionID = testCaseVersionID;
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

    public int getLocatingMethodID() {
        return locatingMethodID;
    }

    public void setLocatingMethodID(int locatingMethodID) {
        this.locatingMethodID = locatingMethodID;
    }

    public int getBreakageReasonID() {
        return breakageReasonID;
    }

    public void setBreakageReasonID(int breakageReasonID) {
        this.breakageReasonID = breakageReasonID;
    }
}
