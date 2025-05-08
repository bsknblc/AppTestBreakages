package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Integer> {
    public Repair getReferenceById(int id);

}