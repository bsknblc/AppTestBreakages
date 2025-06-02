package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.TestSuiteDTO;
import com.WebApps.Benchmark.Mapper.TestSuiteMapper;
import com.WebApps.Benchmark.Model.TestSuite;
import com.WebApps.Benchmark.Repository.ApplicationRepository;
import com.WebApps.Benchmark.Repository.TestSuiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestSuiteService {

    private final TestSuiteRepository testSuiteRepository;
    private final ApplicationRepository applicationRepository;
    public TestSuiteService(TestSuiteRepository testSuiteRepository, ApplicationRepository applicationRepository) {
        this.testSuiteRepository = testSuiteRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<TestSuiteDTO> findAll(){
        List<TestSuite> testSuites = testSuiteRepository.findAll();
        return testSuites.stream()
                .map(TestSuiteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TestSuiteDTO findById(int id){
        TestSuite testSuite = testSuiteRepository.getReferenceById(id);
        return TestSuiteMapper.toDTO(testSuite);
    }

    public List<TestSuiteDTO> findByApplication_Id(int applicationId){
        List<TestSuite> testSuites = testSuiteRepository.findByApplication_Id(applicationId);
        return testSuites.stream()
                .map(TestSuiteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TestSuiteDTO save(TestSuiteDTO testSuiteDTO) {
        TestSuite testSuite = new TestSuite();
        testSuite.setTestSuiteName(testSuiteDTO.getTestSuiteName());
        testSuite.setUrl(testSuiteDTO.getUrl());
        testSuite.setApplication(applicationRepository.getReferenceById(testSuiteDTO.getApplicationId()));
        testSuiteRepository.save(testSuite);

        testSuiteDTO.setId(testSuite.getId());
        return testSuiteDTO;
    }

}
