package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.TestCaseVersion;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCaseVersionRepository extends JpaRepository<TestCaseVersion, Integer> {

    public TestCaseVersion getReferenceById(int id);

    List<TestCaseVersion> findByTestCase_Id(@NotNull int testCaseId);
}

