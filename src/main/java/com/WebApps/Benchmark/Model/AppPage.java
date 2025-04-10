package com.WebApps.Benchmark.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_APP_PAGE")
public class AppPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "PAGE_NAME")
    private String pageName;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    @JsonBackReference
    private Application application;

    @OneToMany(mappedBy = "appPage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineOfCode> lineOfCodes = new ArrayList<>();

    public AppPage() {}

    public AppPage(String pageName, Application application) {
        this.pageName = pageName;
        this.application = application;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<LineOfCode> getLineOfCodes() {
        return lineOfCodes;
    }

    public void setLineOfCodes(List<LineOfCode> lineOfCodes) {
        this.lineOfCodes = lineOfCodes;
    }
}
