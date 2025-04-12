package com.WebApps.Benchmark.DTO;

import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Model.LineOfCode;

import java.util.ArrayList;
import java.util.List;

public class AppPageDTO {

    private int id;
    private String pageName;
    private int applicationId;
    //private List<LineOfCode> lineOfCodes = new ArrayList<>();

    public AppPageDTO() {}

/*    public AppPageDTO(int id, String pageName, Application application, List<LineOfCode> lineOfCodes) {
        this.id = id;
        this.pageName = pageName;
        this.application = application;
        this.lineOfCodes = lineOfCodes;
    }*/

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

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

/*    public List<LineOfCode> getLineOfCodes() {
        return lineOfCodes;
    }

    public void setLineOfCodes(List<LineOfCode> lineOfCodes) {
        this.lineOfCodes = lineOfCodes;
    }*/
}
