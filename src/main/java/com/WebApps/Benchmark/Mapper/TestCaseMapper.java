package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.TestCaseDTO;
import com.WebApps.Benchmark.DTO.TestCaseVersionDTO;
import com.WebApps.Benchmark.Model.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestCaseMapper {

    public static TestCaseDTO toDTO(TestCase entity) {
        if (entity == null) return null;

        List<TestCaseVersionDTO> versionDTOs = entity.getTestCaseVersions() != null
                ? entity.getTestCaseVersions().stream()
                .map(TestCaseVersionMapper::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return new TestCaseDTO(
                entity.getId(),
                entity.getTestCaseName(),
                entity.getDescription(),
                entity.getTestSuite() != null ? entity.getTestSuite().getId() : 0,
                versionDTOs
        );
    }

}
