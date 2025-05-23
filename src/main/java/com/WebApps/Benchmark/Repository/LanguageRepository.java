package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    public Language getReferenceById(int id);
}
