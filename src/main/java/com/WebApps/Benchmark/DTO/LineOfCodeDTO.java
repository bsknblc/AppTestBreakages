package com.WebApps.Benchmark.DTO;

import com.WebApps.Benchmark.Model.AppPage;
import com.WebApps.Benchmark.Model.TestCaseVersion;

public class LineOfCodeDTO {

    private int id;
    private int appPageID;
    private int testCaseVersionID;
    private RepairALineOfCodeDTO repairALineOfCode;

    public LineOfCodeDTO() {}

    public LineOfCodeDTO(int id, int appPageID, int testCaseVersionID, RepairALineOfCodeDTO repairALineOfCode) {
        this.id = id;
        this.appPageID = appPageID;
        this.testCaseVersionID = testCaseVersionID;
        this.repairALineOfCode = repairALineOfCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppPageID() {
        return appPageID;
    }

    public void setAppPageID(int appPagID) {
        this.appPageID = appPagID;
    }

    public int getTestCaseVersionID() {
        return testCaseVersionID;
    }

    public void setTestCaseVersionID(int testCaseVersionID) {
        this.testCaseVersionID = testCaseVersionID;
    }

    public RepairALineOfCodeDTO getRepairALineOfCode() {
        return repairALineOfCode;
    }

    public void setRepairALineOfCode(RepairALineOfCodeDTO repairALineOfCode) {
        this.repairALineOfCode = repairALineOfCode;
    }
}
