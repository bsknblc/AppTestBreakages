package com.WebApps.Benchmark.DTO;

import java.util.ArrayList;
import java.util.List;

public class BreakageDTO {

    private int id;
    private int appReleaseID;
    private int testCaseVersionID;
    private List<RepairALineOfCodeDTO> repairALineOfCodes = new ArrayList<>();
    private String taxonomyDescription;

    public BreakageDTO() {}

    public BreakageDTO(int id, int appReleaseID, int testCaseVersionID, List<RepairALineOfCodeDTO> repairALineOfCodes, String taxonomyDescription) {
        this.id = id;
        this.appReleaseID = appReleaseID;
        this.testCaseVersionID = testCaseVersionID;
        this.repairALineOfCodes = repairALineOfCodes;
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

    public List<RepairALineOfCodeDTO> getRepairALineOfCodes() {
        return repairALineOfCodes;
    }

    public void setRepairALineOfCodes(List<RepairALineOfCodeDTO> repairALineOfCodes) {
        this.repairALineOfCodes = repairALineOfCodes;
    }

    public String getTaxonomyDescription() {
        return taxonomyDescription;
    }

    public void setTaxonomyDescription(String taxonomyDescription) {
        this.taxonomyDescription = taxonomyDescription;
    }
}
