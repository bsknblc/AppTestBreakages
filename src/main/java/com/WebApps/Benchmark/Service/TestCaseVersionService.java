package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.TestCaseVersionDTO;
import com.WebApps.Benchmark.Mapper.TestCaseVersionMapper;
import com.WebApps.Benchmark.Model.TestCaseVersion;
import com.WebApps.Benchmark.Repository.TestCaseRepository;
import com.WebApps.Benchmark.Repository.TestCaseVersionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestCaseVersionService {

    private final TestCaseVersionRepository testCaseVersionRepository;
    private final TestCaseRepository testCaseRepository;
    public TestCaseVersionService(TestCaseVersionRepository testCaseVersionRepository, TestCaseRepository testCaseRepository) {
        this.testCaseVersionRepository = testCaseVersionRepository;
        this.testCaseRepository = testCaseRepository;
    }

    public List<TestCaseVersionDTO> findAll(){
        List<TestCaseVersion> testCaseVersions = testCaseVersionRepository.findAll();
        return testCaseVersions.stream()
                .map(TestCaseVersionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TestCaseVersionDTO findById(int id){
        TestCaseVersion testCaseVersion = testCaseVersionRepository.getReferenceById(id);
        return TestCaseVersionMapper.toDTO(testCaseVersion);
    }

    public TestCaseVersionDTO save(TestCaseVersionDTO testCaseVersionDTO) {
        TestCaseVersion testCaseVersion = new TestCaseVersion();
        testCaseVersion.setTestCaseVersionName(testCaseVersionDTO.getTestCaseVersionName());
        testCaseVersion.setTestCase(testCaseRepository.getReferenceById(testCaseVersionDTO.getTestCaseId()));
        testCaseVersionRepository.save(testCaseVersion);

        testCaseVersionDTO.setId(testCaseVersion.getId());
        return testCaseVersionDTO;
    }
}
