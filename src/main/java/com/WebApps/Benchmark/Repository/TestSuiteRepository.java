package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.TestSuite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestSuiteRepository extends JpaRepository<TestSuite, Integer> {

    public TestSuite getReferenceById(int id);

}
