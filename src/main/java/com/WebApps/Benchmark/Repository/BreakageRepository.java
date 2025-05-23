package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.Breakage;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreakageRepository extends JpaRepository<Breakage, Integer> {

    public Breakage getReferenceById(int id);

    List<Breakage> findByTestCaseVersion_Id(@NotNull int testCaseVersionId);

    List<Breakage> findByBreakageReason_Id(@NotNull int breakageReasonId);

    List<Breakage> findByLocatingMethod_Id(@NotNull int locatingMethodId);
}
