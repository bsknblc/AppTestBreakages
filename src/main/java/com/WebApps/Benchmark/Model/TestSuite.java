package com.WebApps.Benchmark.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_TEST_SUITE")
public class TestSuite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "NAME")
    private String testSuiteName;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @OneToMany(mappedBy = "testSuite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestCase> testCases = new ArrayList<>();

    public TestSuite(){}

    public TestSuite(String testSuiteName, Application application) {
        this.application = application;
        this.testSuiteName = testSuiteName;
    }

    public int getId() {
        return id;
    }

    public String getTestSuiteName() {
        return testSuiteName;
    }

    public void setTestSuiteName(String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
