package com.WebApps.Benchmark.DTO;

public class BreakageExplanationDTO {

    private int id;
    private String explanation;

    public BreakageExplanationDTO() {}

    public BreakageExplanationDTO(int id, String explanation) {
        this.id = id;
        this.explanation = explanation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
