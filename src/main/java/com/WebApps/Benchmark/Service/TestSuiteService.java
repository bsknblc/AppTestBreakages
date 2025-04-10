package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.TestSuiteDTO;
import com.WebApps.Benchmark.Model.TestSuite;
import com.WebApps.Benchmark.Repository.TestSuiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestSuiteService {
    @Autowired
    TestSuiteRepository testSuiteRepository;

    public List<TestSuiteDTO> findAll(){
        List<TestSuite> testSuites = testSuiteRepository.findAll();
        List<TestSuiteDTO> testSuiteDTOS = new ArrayList<>();
        for (TestSuite testSuite: testSuites) {
            testSuiteDTOS.add(new TestSuiteDTO(testSuite.getId(), testSuite.getTestSuiteName(), testSuite.getTestCases()));
        }
        return testSuiteDTOS;
    }

    public TestSuiteDTO findById(int id){
        TestSuite testSuite = testSuiteRepository.getReferenceById(id);
        return new TestSuiteDTO(testSuite.getId(), testSuite.getTestSuiteName(), testSuite.getTestCases());
    }

    public TestSuiteDTO save(TestSuite testSuite) {
        TestSuite testSuite1 = testSuiteRepository.save(testSuite);
        return new TestSuiteDTO(testSuite.getId(), testSuite.getTestSuiteName(), testSuite.getTestCases());
    }

}
