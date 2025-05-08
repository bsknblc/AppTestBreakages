package com.WebApps.Benchmark.DTO;



import java.util.ArrayList;
import java.util.List;

public class LocatingMethodDTO {

    private int id;
    private String locatingMethodName;
    private List<BreakageDTO> breakages = new ArrayList<>();

    public LocatingMethodDTO() {}

    public LocatingMethodDTO(int id, String locatingMethodName, List<BreakageDTO> breakages) {
        this.id = id;
        this.locatingMethodName = locatingMethodName;
        this.breakages = breakages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocatingMethodName() {
        return locatingMethodName;
    }

    public void setLocatingMethodName(String locatingMethodName) {
        this.locatingMethodName = locatingMethodName;
    }

    public List<BreakageDTO> getBreakages() {
        return breakages;
    }

    public void setBreakages(List<BreakageDTO> breakages) {
        this.breakages = breakages;
    }
}
