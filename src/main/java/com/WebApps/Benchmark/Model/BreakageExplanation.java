package com.WebApps.Benchmark.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_BREAKAGE_EXPLANATION")
public class BreakageExplanation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @ManyToMany(mappedBy = "breakageExplanations")
    private List<Breakage> breakages = new ArrayList<>();

    @Column(name = "EXPLANATION")
    @NotNull
    private String explanation;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    @NotNull
    private CauseType type;

    public BreakageExplanation() {}

    public BreakageExplanation(List<Breakage> breakages, String explanation) {
        this.breakages = breakages;
        this.explanation = explanation;
    }

    public int getId() {
        return id;
    }

    public List<Breakage> getBreakages() {
        return breakages;
    }

    public void setBreakages(List<Breakage> breakage) {
        this.breakages = breakage;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public CauseType getType() {
        return type;
    }

    public void setType(CauseType type) {
        this.type = type;
    }
}
