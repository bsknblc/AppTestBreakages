package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.RepairALineOfCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairALineOfCodeRepository extends JpaRepository<RepairALineOfCode, Integer> {
    public RepairALineOfCode getReferenceById(int id);

}