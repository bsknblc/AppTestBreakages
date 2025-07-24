package com.WebApps.Benchmark.DTO;

public class BreakageExplanationDTO {

    private int id;
    private String explanation;
    private int typeId;

    public BreakageExplanationDTO() {}

    public BreakageExplanationDTO(int id, String explanation, int typeId) {
        this.id = id;
        this.explanation = explanation;
        this.typeId = typeId;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
