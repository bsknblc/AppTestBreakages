package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.DTO.ExplanationStats;
import com.WebApps.Benchmark.Model.Breakage;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreakageRepository extends JpaRepository<Breakage, Integer> {

    public Breakage getReferenceById(int id);

    List<Breakage> findByTestCaseVersion_Id(@NotNull int testCaseVersionId);

    List<Breakage> findByBreakageReason_Id(@NotNull int breakageReasonId);

    List<Breakage> findByLocatingMethod_Id(@NotNull int locatingMethodId);

    @Query("SELECT b FROM Breakage b JOIN b.breakageExplanations be WHERE be.explanation IN :explanations")
    List<Breakage> findByBreakageExplanationTexts(@Param("explanations") List<String> explanations);

    @Query("SELECT be.id AS id, be.explanation AS explanation, ct.causeType AS causeType, COUNT(b.id) AS count " +
            "FROM Breakage b " +
            "JOIN b.breakageExplanations be " +
            "JOIN be.type ct " +
            "GROUP BY be.id, be.explanation, ct.causeType")
    List<ExplanationStats> countBreakagesGroupedByExplanation();

}
