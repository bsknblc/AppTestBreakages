package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.DTO.ExplanationStats;
import com.WebApps.Benchmark.Model.Repair;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Integer> {
    public Repair getReferenceById(int id);

    List<Repair> findByBreakage_Id(@NotNull int breakageId);

    @Query("SELECT r FROM Repair r JOIN r.repairExplanations re WHERE re.explanation IN :explanations")
    List<Repair> findByRepairExplanationTexts(@Param("explanations") List<String> explanations);

    @Query("SELECT re.id AS id, re.explanation AS explanation, COUNT(r.id) AS count " +
            "FROM Repair r " +
            "JOIN r.repairExplanations re " +
            "GROUP BY re.id, re.explanation")
    List<ExplanationStats> countRepairsGroupedByExplanation();

    @Query("SELECT re.id AS id, re.explanation AS explanation, COUNT(r.id) AS count " +
            "FROM Repair r " +
            "JOIN r.repairExplanations re " +
            "JOIN r.breakage b " +
            "JOIN b.appRelease ar " +
            "JOIN ar.application app " +
            "WHERE app.id IN (1, 3, 4, 5) " +
            "GROUP BY re.id, re.explanation")
    List<ExplanationStats> countRepairsGroupedByExplanationWithoutValidation();

}