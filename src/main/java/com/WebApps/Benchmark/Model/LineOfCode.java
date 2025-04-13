package com.WebApps.Benchmark.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "T_LINE_OF_CODE")
public class LineOfCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "app_page_id", nullable = false)
    private AppPage appPage;

    @ManyToOne
    @JoinColumn(name = "test_case_version_id", nullable = false)
    private TestCaseVersion testCaseVersion;

    @OneToOne(mappedBy = "lineOfCode")
    private RepairALineOfCode repairALineOfCode;

    public LineOfCode(){}

    public LineOfCode(AppPage appPage, TestCaseVersion testCaseVersion) {
        this.appPage = appPage;
        this.testCaseVersion = testCaseVersion;
    }

    public int getId() {
        return id;
    }

    public AppPage getAppPage() {
        return appPage;
    }

    public void setAppPage(AppPage appPage) {
        this.appPage = appPage;
    }

    public TestCaseVersion getTestCaseVersion() {
        return testCaseVersion;
    }

    public void setTestCaseVersion(TestCaseVersion testCaseVersion) {
        this.testCaseVersion = testCaseVersion;
    }

    public RepairALineOfCode getRepairALineOfCode() {
        return repairALineOfCode;
    }

    public void setRepairALineOfCode(RepairALineOfCode repairALineOfCode) {
        this.repairALineOfCode = repairALineOfCode;
    }
}
