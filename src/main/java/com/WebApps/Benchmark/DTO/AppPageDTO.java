package com.WebApps.Benchmark.DTO;

import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Model.LineOfCode;

import java.util.ArrayList;
import java.util.List;

public class AppPageDTO {

    private int id;
    private String pageName;
    private int applicationId;
    private List<LineOfCodeDTO> lineOfCodes = new ArrayList<>();

    public AppPageDTO() {}

    public AppPageDTO(int id, String pageName, int applicationId, List<LineOfCodeDTO> lineOfCodes) {
        this.id = id;
        this.pageName = pageName;
        this.applicationId = applicationId;
        this.lineOfCodes = lineOfCodes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public int getApplication() {
        return applicationId;
    }

    public void setApplication(int applicationId) {
        this.applicationId = applicationId;
    }

    public List<LineOfCodeDTO> getLineOfCodes() {
        return lineOfCodes;
    }

    public void setLineOfCodes(List<LineOfCodeDTO> lineOfCodes) {
        this.lineOfCodes = lineOfCodes;
    }
}
