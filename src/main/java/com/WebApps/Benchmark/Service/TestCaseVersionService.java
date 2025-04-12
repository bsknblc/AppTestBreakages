package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.DTO.TestCaseVersionDTO;
import com.WebApps.Benchmark.Model.Breakage;
import com.WebApps.Benchmark.Model.TestCaseVersion;
import com.WebApps.Benchmark.Repository.TestCaseVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestCaseVersionService {
    @Autowired
    TestCaseVersionRepository testCaseVersionRepository;

    public List<TestCaseVersionDTO> findAll(){
        List<TestCaseVersion> testCaseVersions = testCaseVersionRepository.findAll();
        List<TestCaseVersionDTO> testCaseVersionDTOs = new ArrayList<>();
        for (TestCaseVersion testCaseVersion: testCaseVersions) {
            testCaseVersionDTOs.add(new TestCaseVersionDTO());
        }
        return testCaseVersionDTOs;
    }

    public TestCaseVersionDTO findById(int id){
        TestCaseVersion testCaseVersion = testCaseVersionRepository.getReferenceById(id);
        return new TestCaseVersionDTO();
    }

    public TestCaseVersionDTO save(TestCaseVersion testCaseVersion1) {
        TestCaseVersion testCaseVersion = testCaseVersionRepository.save(testCaseVersion1);
        return new TestCaseVersionDTO();
    }
}
