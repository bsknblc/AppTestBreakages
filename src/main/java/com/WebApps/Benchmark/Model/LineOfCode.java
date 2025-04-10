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

}
