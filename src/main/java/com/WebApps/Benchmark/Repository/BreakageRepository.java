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

    @Query("SELECT be.id AS id, be.explanation AS explanation, ct.causeType AS causeType, COUNT(b.id) AS count " +
            "FROM Breakage b " +
            "JOIN b.breakageExplanations be " +
            "JOIN be.type ct " +
            "JOIN b.appRelease ar " +
            "JOIN ar.application app " +
            "WHERE app.id IN (1, 3, 4, 5) " +
            "GROUP BY be.id, be.explanation, ct.causeType")
    List<ExplanationStats> countBreakagesGroupedByExplanationWithoutValidation();

    @Query("""
        SELECT be.explanation, a.appName, COUNT(b)
        FROM BreakageExplanation be
        JOIN be.breakages b
        JOIN b.appRelease ar
        JOIN ar.application a
        GROUP BY be.explanation, a.appName
    """)
    List<Object[]> countBreakagesByAppAndExplanation();

    @Query("""
        SELECT br.reasonName, COUNT(b)
        FROM Breakage b
        JOIN b.breakageReason br
        JOIN b.appRelease ar
        JOIN ar.application a
        WHERE a.id IN (1, 3, 4, 5, 6, 8)
        GROUP BY br.reasonName
    """)
    List<Object[]> countBreakagesByReasonExcludingApps();

    @Query("""
        SELECT lm.locatingMethodName, COUNT(b)
        FROM Breakage b
        JOIN b.locatingMethod lm
        JOIN b.appRelease ar
        JOIN ar.application a
        WHERE a.id IN (1,3,4,5,6,8)
        GROUP BY lm.locatingMethodName
    """)
    List<Object[]> countBreakagesByLocatingMethodExcludingApps();

}
