package com.WebApps.Benchmark.DTO;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDTO {

    private int id;
    private String appName;
    private String url;
    private List<AppReleaseDTO> releases = new ArrayList<>();
    private List<TestSuiteDTO> testSuites = new ArrayList<>();
    private List<LanguageDTO> languages = new ArrayList<>();

    public ApplicationDTO() {}

    public ApplicationDTO(int id, String appName, String url, List<AppReleaseDTO> releases, List<TestSuiteDTO> testSuites, List<LanguageDTO> languages) {
        this.id = id;
        this.appName = appName;
        this.url = url;
        this.releases = releases;
        this.testSuites = testSuites;
        this.languages = languages;
    }

    public int getId() {
        return id;
    }

    public String getAppName() {
        return appName;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<AppReleaseDTO> getReleases() {
        return releases;
    }

    public void setReleases(List<AppReleaseDTO> releases) {
        this.releases = releases;
    }

    public List<TestSuiteDTO> getTestSuites() {
        return testSuites;
    }

    public void setTestSuites(List<TestSuiteDTO> testSuites) {
        this.testSuites = testSuites;
    }

    public List<LanguageDTO> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageDTO> languages) {
        this.languages = languages;
    }
}
