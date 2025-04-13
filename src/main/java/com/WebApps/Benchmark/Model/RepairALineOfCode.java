package com.WebApps.Benchmark.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "T_REPAIR_A_LINE_OF_CODE")
public class RepairALineOfCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "line_of_code_id", referencedColumnName = "id")
    @NotNull
    private LineOfCode lineOfCode;

    @ManyToOne
    @JoinColumn(name = "breakage_id", nullable = false)
    @NotNull
    private Breakage breakage;

    public RepairALineOfCode() {}

    public RepairALineOfCode(LineOfCode lineOfCode, Breakage breakage) {
        this.lineOfCode = lineOfCode;
        this.breakage = breakage;
    }

    public int getId() {
        return id;
    }

    public LineOfCode getLineOfCode() {
        return lineOfCode;
    }

    public void setLineOfCode(LineOfCode lineOfCode) {
        this.lineOfCode = lineOfCode;
    }

    public Breakage getBreakage() {
        return breakage;
    }

    public void setBreakage(Breakage breakage) {
        this.breakage = breakage;
    }
}
