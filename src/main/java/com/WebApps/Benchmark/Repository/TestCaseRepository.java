package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Integer> {

    public TestCase getReferenceById(int id);

}

