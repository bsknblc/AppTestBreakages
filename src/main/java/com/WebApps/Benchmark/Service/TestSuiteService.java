package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.TestSuiteDTO;
import com.WebApps.Benchmark.Mapper.TestSuiteMapper;
import com.WebApps.Benchmark.Model.TestSuite;
import com.WebApps.Benchmark.Repository.TestSuiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestSuiteService {

    private final TestSuiteRepository testSuiteRepository;
    public TestSuiteService(TestSuiteRepository testSuiteRepository) {
        this.testSuiteRepository = testSuiteRepository;
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

    public TestSuiteDTO save(TestSuiteDTO testSuiteDTO) {
        TestSuite testSuite = TestSuiteMapper.toEntity(testSuiteDTO);
        testSuiteRepository.save(testSuite);
        testSuiteDTO.setId(testSuite.getId());
        return testSuiteDTO;
    }

}
