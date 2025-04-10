package com.WebApps.Benchmark.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_BREAKAGE")
public class Breakage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "app_release_id", nullable = false)
    private AppRelease appRelease;

    @ManyToOne
    @JoinColumn(name = "test_case_version_id", nullable = false)
    private TestCaseVersion testCaseVersion;

    @OneToMany(mappedBy = "breakage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RepairALineOfCode> repairALineOfCodes = new ArrayList<>();

}
