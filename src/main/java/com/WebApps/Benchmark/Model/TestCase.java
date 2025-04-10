package com.WebApps.Benchmark.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_TEST_CASE")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "NAME")
    private String testCaseName;

    @OneToMany(mappedBy = "testCase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestCaseVersion> testCaseVersions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "test_suite_id", nullable = false)
    private TestSuite testSuite;

}
