package com.WebApps.Benchmark.DTO;

public class RepairDTO {
    private int id;
    private int breakageId;
    private String commitHash;

    public RepairDTO() {}

    public RepairDTO(int id, int breakageId, String commitHash) {
        this.id = id;
        this.breakageId = breakageId;
        this.commitHash = commitHash;
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
}
