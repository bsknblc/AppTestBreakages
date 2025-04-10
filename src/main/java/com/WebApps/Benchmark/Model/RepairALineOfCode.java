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
    private LineOfCode lineOfCode;

    @ManyToOne
    @JoinColumn(name = "breakage_id", nullable = false)
    private Breakage breakage;

}
