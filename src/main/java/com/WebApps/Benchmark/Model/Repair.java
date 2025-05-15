package com.WebApps.Benchmark.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "T_REPAIR")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "breakage_id", nullable = false)
    @NotNull
    private Breakage breakage;

    @Column(name = "COMMIT_HASH")
    @NotNull
    private String commitHash;

    public Repair() {}

    public Repair(Breakage breakage) {
        this.breakage = breakage;
    }

    public int getId() {
        return id;
    }

    public Breakage getBreakage() {
        return breakage;
    }

    public void setBreakage(Breakage breakage) {
        this.breakage = breakage;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

}
