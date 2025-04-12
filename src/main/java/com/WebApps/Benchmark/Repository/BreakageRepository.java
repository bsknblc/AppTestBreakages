package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.Breakage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreakageRepository extends JpaRepository<Breakage, Integer> {

    public Breakage getReferenceById(int id);

}
