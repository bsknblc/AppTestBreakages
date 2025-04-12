package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.TestCaseVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseVersionRepository extends JpaRepository<TestCaseVersion, Integer> {

    public TestCaseVersion getReferenceById(int id);

}

