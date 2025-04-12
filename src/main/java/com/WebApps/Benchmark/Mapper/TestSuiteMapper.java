package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.TestCaseDTO;
import com.WebApps.Benchmark.DTO.TestSuiteDTO;
import com.WebApps.Benchmark.Model.TestCase;
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
                testCaseDTOs
        );
    }

    public static TestSuite toEntity(TestSuiteDTO dto) {
        if (dto == null) return null;

        TestSuite entity = new TestSuite();
        entity.setTestSuiteName(dto.getTestSuiteName());

        if (dto.getTestCases() != null) {
            List<TestCase> testCases = dto.getTestCases().stream()
                    .map(testCaseDto -> {
                        TestCase testCase = TestCaseMapper.toEntity(testCaseDto, entity);
                        testCase.setTestSuite(entity); // Set back-reference
                        return testCase;
                    })
                    .collect(Collectors.toList());
            entity.setTestCases(testCases);
        }

        return entity;
    }
}
