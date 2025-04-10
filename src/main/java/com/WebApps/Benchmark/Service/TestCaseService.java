package com.WebApps.Benchmark.Service;


import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.DTO.TestCaseDTO;
import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Model.TestCase;
import com.WebApps.Benchmark.Repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestCaseService {
    @Autowired
    TestCaseRepository testCaseRepository;

    public List<TestCaseDTO> findAll(){
        List<TestCase> testCases = testCaseRepository.findAll();
        List<TestCaseDTO> testCaseDTOS = new ArrayList<>();
        for (TestCase testCase: testCases) {
            testCaseDTOS.add(new TestCaseDTO(testCase.getId(), testCase.getTestCaseName(), testCase.getTestCaseVersions(), testCase.getTestSuite(), testCase.getDescription()));
        }
        return testCaseDTOS;
    }

    public TestCaseDTO findById(int id){
        TestCase testCase = testCaseRepository.getReferenceById(id);
        return new TestCaseDTO(testCase.getId(), testCase.getTestCaseName(), testCase.getTestCaseVersions(), testCase.getTestSuite(), testCase.getDescription());
    }

    public TestCaseDTO save(TestCase testCase1) {
        TestCase testCase = testCaseRepository.save(testCase1);
        return new TestCaseDTO(testCase.getId(), testCase.getTestCaseName(), testCase.getTestCaseVersions(), testCase.getTestSuite(), testCase.getDescription());
    }

}
