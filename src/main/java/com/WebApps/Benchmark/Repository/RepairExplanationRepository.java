package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.RepairExplanation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairExplanationRepository extends JpaRepository<RepairExplanation, Integer> {

    public RepairExplanation getReferenceById(int id);

}
