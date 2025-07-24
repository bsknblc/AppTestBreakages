package com.WebApps.Benchmark.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_CAUSE_TYPE")
public class CauseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "CAUSE_TYPE")
    private String causeType;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BreakageExplanation> breakageExplanations = new ArrayList<>();

    public CauseType() {}

    public CauseType(String causeType) {
        this.causeType = causeType;
    }

    public int getId() {
        return id;
    }

    public String getCauseType() {
        return causeType;
    }

    public void setCauseType(String causeType) {
        this.causeType = causeType;
    }

    public List<BreakageExplanation> getBreakageExplanations() {
        return breakageExplanations;
    }

    public void setBreakageExplanations(List<BreakageExplanation> breakageExplanations) {
        this.breakageExplanations = breakageExplanations;
    }
}
