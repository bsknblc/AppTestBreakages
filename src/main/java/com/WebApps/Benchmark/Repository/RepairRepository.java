package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.Repair;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Integer> {
    public Repair getReferenceById(int id);

    List<Repair> findByBreakage_Id(@NotNull int breakageId);
}