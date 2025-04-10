package com.WebApps.Benchmark.DTO;

import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Model.LineOfCode;

import java.util.ArrayList;
import java.util.List;

public class AppPageDTO {

    private int id;
    private String pageName;
    private Application application;
    private List<LineOfCode> lineOfCodes = new ArrayList<>();

    public AppPageDTO(int id, String pageName, Application application, List<LineOfCode> lineOfCodes) {
        this.id = id;
        this.pageName = pageName;
        this.application = application;
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

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<LineOfCode> getLineOfCodes() {
        return lineOfCodes;
    }

    public void setLineOfCodes(List<LineOfCode> lineOfCodes) {
        this.lineOfCodes = lineOfCodes;
    }
}
