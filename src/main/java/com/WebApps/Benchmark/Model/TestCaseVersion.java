package com.WebApps.Benchmark.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_TEST_CASE_VERSION")
public class TestCaseVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "NAME")
    private String testCaseVersionName;

    @OneToMany(mappedBy = "testCaseVersion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineOfCode> lineOfCodes = new ArrayList<>();

    @OneToMany(mappedBy = "testCaseVersion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Breakage> breakages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "test_case_id", nullable = false)
    private TestCase testCase;


}
