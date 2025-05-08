package com.WebApps.Benchmark.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_LOCATING_METHOD")
public class LocatingMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "LOCATING_METHOD_NAME")
    private String locatingMethodName;

    @OneToMany(mappedBy = "locatingMethod", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Breakage> breakages = new ArrayList<>();

    public LocatingMethod() {}

    public LocatingMethod(String locatingMethodName) {
        this.locatingMethodName = locatingMethodName;
    }

    public int getId() {
        return id;
    }

    public String getLocatingMethodName() {
        return locatingMethodName;
    }

    public void setLocatingMethodName(String locatingMethodName) {
        this.locatingMethodName = locatingMethodName;
    }

    public List<Breakage> getBreakages() {
        return breakages;
    }

    public void setBreakages(List<Breakage> breakages) {
        this.breakages = breakages;
    }
}
