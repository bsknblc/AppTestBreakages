package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.BreakageExplanation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreakageExplanationRepository extends JpaRepository<BreakageExplanation, Integer> {

    public BreakageExplanation getReferenceById(int id);

}
