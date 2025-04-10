package com.WebApps.Benchmark.DTO;

import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Model.Breakage;

import java.util.ArrayList;
import java.util.List;

public class AppReleaseDTO {

    private int id;
    private String releaseName;
    private List<Breakage> breakages = new ArrayList<>();
    private Application application;

    public AppReleaseDTO(int id, String releaseName, List<Breakage> breakages, Application application) {
        this.id = id;
        this.releaseName = releaseName;
        this.breakages = breakages;
        this.application = application;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public List<Breakage> getBreakages() {
        return breakages;
    }

    public void setBreakages(List<Breakage> breakages) {
        this.breakages = breakages;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
