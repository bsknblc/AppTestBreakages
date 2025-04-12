package com.WebApps.Benchmark.DTO;

import com.WebApps.Benchmark.Model.AppRelease;
import com.WebApps.Benchmark.Model.RepairALineOfCode;
import com.WebApps.Benchmark.Model.TestCaseVersion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BreakageDTO {

    private int id;
    private AppRelease appRelease;
    private TestCaseVersion testCaseVersion;
    private List<RepairALineOfCode> repairALineOfCodes = new ArrayList<>();
    private String taxonomyDescription;

    public BreakageDTO(int id, AppRelease appRelease, TestCaseVersion testCaseVersion, List<RepairALineOfCode> repairALineOfCodes, String taxonomyDescription) {
        this.id = id;
        this.appRelease = appRelease;
        this.testCaseVersion = testCaseVersion;
        this.repairALineOfCodes = repairALineOfCodes;
        this.taxonomyDescription = taxonomyDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppRelease getAppRelease() {
        return appRelease;
    }

    public void setAppRelease(AppRelease appRelease) {
        this.appRelease = appRelease;
    }

    public TestCaseVersion getTestCaseVersion() {
        return testCaseVersion;
    }

    public void setTestCaseVersion(TestCaseVersion testCaseVersion) {
        this.testCaseVersion = testCaseVersion;
    }

    public List<RepairALineOfCode> getRepairALineOfCodes() {
        return repairALineOfCodes;
    }

    public void setRepairALineOfCodes(List<RepairALineOfCode> repairALineOfCodes) {
        this.repairALineOfCodes = repairALineOfCodes;
    }

    public String getTaxonomyDescription() {
        return taxonomyDescription;
    }

    public void setTaxonomyDescription(String taxonomyDescription) {
        this.taxonomyDescription = taxonomyDescription;
    }
}
