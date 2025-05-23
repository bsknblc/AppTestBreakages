package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.TestSuite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestSuiteRepository extends JpaRepository<TestSuite, Integer> {

    public TestSuite getReferenceById(int id);

    List<TestSuite> findByApplication_Id(int applicationId);
}
