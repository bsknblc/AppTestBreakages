package com.WebApps.Benchmark.DTO;

import com.WebApps.Benchmark.Model.AppPage;
import com.WebApps.Benchmark.Model.AppRelease;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDTO {

    private int id;
    private String appName;
    private String url;
    private List<AppRelease> releases = new ArrayList<>();
    private List<AppPage> pages = new ArrayList<>();

    public ApplicationDTO(int id, String appName, String url, List<AppRelease> releases, List<AppPage> pages) {
        this.id = id;
        this.appName = appName;
        this.url = url;
        this.releases = releases;
        this.pages = pages;
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

    public List<AppRelease> getReleases() {
        return releases;
    }

    public List<AppPage> getPages() {
        return pages;
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

    public void setReleases(List<AppRelease> releases) {
        this.releases = releases;
    }

    public void setPages(List<AppPage> pages) {
        this.pages = pages;
    }
}
