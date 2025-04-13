package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.TestCaseDTO;
import com.WebApps.Benchmark.Mapper.TestCaseMapper;
import com.WebApps.Benchmark.Model.TestCase;
import com.WebApps.Benchmark.Repository.TestCaseRepository;
import com.WebApps.Benchmark.Repository.TestSuiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestCaseService {

    private final TestCaseRepository testCaseRepository;
    private final TestSuiteRepository testSuiteRepository;

    public TestCaseService(TestCaseRepository testCaseRepository, TestSuiteRepository testSuiteRepository) {
        this.testCaseRepository = testCaseRepository;
        this.testSuiteRepository = testSuiteRepository;
    }

    public List<TestCaseDTO> findAll(){
        List<TestCase> testCases = testCaseRepository.findAll();
        return testCases.stream()
                .map(TestCaseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TestCaseDTO findById(int id){
        TestCase testCase = testCaseRepository.getReferenceById(id);
        return TestCaseMapper.toDTO(testCase);
    }

    public TestCaseDTO save(TestCaseDTO testCaseDTO) {
        TestCase testCase = new TestCase();
        testCase.setTestCaseName(testCaseDTO.getTestCaseName());
        testCase.setDescription(testCaseDTO.getDescription());
        testCase.setTestSuite(testSuiteRepository.getReferenceById(testCaseDTO.getTestSuiteId()));
        testCaseRepository.save(testCase);

        testCaseDTO.setId(testCase.getId());
        return testCaseDTO;
    }

}
