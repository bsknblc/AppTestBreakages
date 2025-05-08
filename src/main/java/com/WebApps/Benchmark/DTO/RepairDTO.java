package com.WebApps.Benchmark.DTO;

public class RepairDTO {
    private int id;
    private int breakageID;

    public RepairDTO() {}

    public RepairDTO(int id, int breakageID) {
        this.id = id;
        this.breakageID = breakageID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBreakageID() {
        return breakageID;
    }

    public void setBreakageID(int breakageID) {
        this.breakageID = breakageID;
    }
}
