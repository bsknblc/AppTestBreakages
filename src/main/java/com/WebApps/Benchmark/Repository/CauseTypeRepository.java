package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.CauseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CauseTypeRepository extends JpaRepository<CauseType, Integer> {
    public CauseType getReferenceById(int id);
}
