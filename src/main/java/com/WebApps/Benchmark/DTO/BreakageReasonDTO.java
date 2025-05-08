package com.WebApps.Benchmark.DTO;


import java.util.ArrayList;
import java.util.List;

public class BreakageReasonDTO {

    private int id;
    private String reasonName;
    private List<BreakageDTO> breakages = new ArrayList<>();

    public BreakageReasonDTO() {}

    public BreakageReasonDTO(int id, String reasonName, List<BreakageDTO> breakages) {
        this.id = id;
        this.reasonName = reasonName;
        this.breakages = breakages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    public List<BreakageDTO> getBreakages() {
        return breakages;
    }

    public void setBreakages(List<BreakageDTO> breakages) {
        this.breakages = breakages;
    }
}
