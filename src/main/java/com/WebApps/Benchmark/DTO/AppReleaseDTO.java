package com.WebApps.Benchmark.DTO;

import java.util.ArrayList;
import java.util.List;

public class AppReleaseDTO {

    private int id;
    private String releaseName;
    private List<BreakageDTO> breakages = new ArrayList<>();
    private int applicationId;

    public AppReleaseDTO() {}

    public AppReleaseDTO(int id, String releaseName, List<BreakageDTO> breakages, int applicationId) {
        this.id = id;
        this.releaseName = releaseName;
        this.breakages = breakages;
        this.applicationId = applicationId;
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

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }
}
