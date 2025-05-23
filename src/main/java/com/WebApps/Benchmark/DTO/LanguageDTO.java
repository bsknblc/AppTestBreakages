package com.WebApps.Benchmark.DTO;

import java.util.ArrayList;
import java.util.List;

public class LanguageDTO {
    private int id;
    private String languageName;

    public LanguageDTO() {}

    public LanguageDTO(int id, String languageName) {
        this.id = id;
        this.languageName = languageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }


}
