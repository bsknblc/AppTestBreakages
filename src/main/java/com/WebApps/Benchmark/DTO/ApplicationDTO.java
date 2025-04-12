package com.WebApps.Benchmark.DTO;

import com.WebApps.Benchmark.Model.AppPage;
import com.WebApps.Benchmark.Model.AppRelease;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDTO {

    private int id;
    private String appName;
    private String url;
    private List<AppReleaseDTO> releases = new ArrayList<>();
    private List<AppPageDTO> pages = new ArrayList<>();

    public ApplicationDTO() {}

    public ApplicationDTO(int id, String appName, String url, List<AppReleaseDTO> releases, List<AppPageDTO> pages) {
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


    public List<AppPageDTO> getPages() {
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

    public List<AppReleaseDTO> getReleases() {
        return releases;
    }

    public void setReleases(List<AppReleaseDTO> releases) {
        this.releases = releases;
    }

    public void setPages(List<AppPageDTO> pages) {
        this.pages = pages;
    }
}
