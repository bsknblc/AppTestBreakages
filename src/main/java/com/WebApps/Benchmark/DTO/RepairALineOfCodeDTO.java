package com.WebApps.Benchmark.DTO;

public class RepairALineOfCodeDTO {
    private int id;
    private int lineOfCodeID;
    private int breakageID;

    public RepairALineOfCodeDTO() {}

    public RepairALineOfCodeDTO(int id, int lineOfCodeID, int breakageID) {
        this.id = id;
        this.lineOfCodeID = lineOfCodeID;
        this.breakageID = breakageID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLineOfCodeID() {
        return lineOfCodeID;
    }

    public void setLineOfCodeID(int lineOfCodeID) {
        this.lineOfCodeID = lineOfCodeID;
    }

    public int getBreakageID() {
        return breakageID;
    }

    public void setBreakageID(int breakageID) {
        this.breakageID = breakageID;
    }
}
