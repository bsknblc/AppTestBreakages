package com.WebApps.Benchmark.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_APPLICATION")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "APP_NAME")
    private String appName;

    @NotNull
    @Column(name = "URL")
    private String url;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AppRelease> releases = new ArrayList<>();

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AppPage> pages = new ArrayList<>();

    public Application() {}

    public Application(String appName, String url) {
        this.appName = appName;
        this.url = url;
    }

    public String getAppName() {
        return appName;
    }

    public int getId() {
        return id;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<AppRelease> getReleases() {
        return releases;
    }

    public void setReleases(List<AppRelease> releases) {
        this.releases = releases;
    }

    public List<AppPage> getPages() {
        return pages;
    }

    public void setPages(List<AppPage> pages) {
        this.pages = pages;
    }
}
