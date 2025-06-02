package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.TestCaseDTO;
import com.WebApps.Benchmark.DTO.TestSuiteDTO;
import com.WebApps.Benchmark.Model.TestSuite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestSuiteMapper {

    public static TestSuiteDTO toDTO(TestSuite entity) {
        if (entity == null) return null;

        List<TestCaseDTO> testCaseDTOs = entity.getTestCases() != null
                ? entity.getTestCases().stream()
                .map(TestCaseMapper::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return new TestSuiteDTO(
                entity.getId(),
                entity.getTestSuiteName(),
                entity.getUrl(),
                entity.getApplication() != null ? entity.getApplication().getId() : 0,
                testCaseDTOs
        );
    }

}
