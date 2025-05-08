package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.BreakageReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreakageReasonRepository extends JpaRepository<BreakageReason, Integer>{

    public BreakageReason getReferenceById(int id);

}
