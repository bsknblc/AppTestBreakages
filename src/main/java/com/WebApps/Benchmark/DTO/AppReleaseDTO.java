package com.WebApps.Benchmark.DTO;

import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Model.Breakage;

import java.util.ArrayList;
import java.util.List;

public class AppReleaseDTO {

    private int id;
    private String releaseName;
    private List<BreakageDTO> breakages = new ArrayList<>();
    private int applicationID;

    public AppReleaseDTO() {}

    public AppReleaseDTO(int id, String releaseName, List<BreakageDTO> breakages, int applicationID) {
        this.id = id;
        this.releaseName = releaseName;
        this.breakages = breakages;
        this.applicationID = applicationID;
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

    public List<BreakageDTO> getBreakages() {
        return breakages;
    }

    public void setBreakages(List<BreakageDTO> breakages) {
        this.breakages = breakages;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }
}
