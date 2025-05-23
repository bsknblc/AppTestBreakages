package com.WebApps.Benchmark.DTO;

import com.WebApps.Benchmark.Model.RepairExplanation;

import java.util.ArrayList;
import java.util.List;

public class RepairDTO {
    private int id;
    private int breakageId;
    private String commitHash;
    private List<RepairExplanationDTO> repairExplanations = new ArrayList<>();

    public RepairDTO() {}

    public RepairDTO(int id, int breakageId, String commitHash, List<RepairExplanationDTO> repairExplanations) {
        this.id = id;
        this.breakageId = breakageId;
        this.commitHash = commitHash;
        this.repairExplanations = repairExplanations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBreakageId() {
        return breakageId;
    }

    public void setBreakageId(int breakageId) {
        this.breakageId = breakageId;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public List<RepairExplanationDTO> getRepairExplanations() {
        return repairExplanations;
    }

    public void setRepairExplanations(List<RepairExplanationDTO> repairExplanations) {
        this.repairExplanations = repairExplanations;
    }
}
