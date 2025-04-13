package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.LineOfCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineOfCodeRepository extends JpaRepository<LineOfCode, Integer> {

    public LineOfCode getReferenceById(int id);

}
