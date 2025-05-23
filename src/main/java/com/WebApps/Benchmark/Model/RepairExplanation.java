package com.WebApps.Benchmark.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_REPAIR_EXPLANATION")
public class RepairExplanation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @Column(name = "EXPLANATION")
    @NotNull
    private String explanation;

    @ManyToMany(mappedBy = "repairExplanations")
    private List<Repair> repairs = new ArrayList<>();

    public RepairExplanation() {}

    public RepairExplanation(String explanation, List<Repair> repairs) {
        this.explanation = explanation;
        this.repairs = repairs;
    }

    public int getId() {
        return id;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repair) {
        this.repairs = repair;
    }
}
