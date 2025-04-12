package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.TestCaseDTO;
import com.WebApps.Benchmark.DTO.TestCaseVersionDTO;
import com.WebApps.Benchmark.Model.TestCase;
import com.WebApps.Benchmark.Model.TestCaseVersion;
import com.WebApps.Benchmark.Model.TestSuite;

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

    public static TestCase toEntity(TestCaseDTO dto, TestSuite parentSuite) {
        if (dto == null) return null;

        TestCase entity = new TestCase();
        entity.setTestCaseName(dto.getTestCaseName());
        entity.setDescription(dto.getDescription());
        entity.setTestSuite(parentSuite);

        if (dto.getTestCaseVersions() != null) {
            List<TestCaseVersion> versions = dto.getTestCaseVersions().stream()
                    .map(versionDto -> {
                        TestCaseVersion version = TestCaseVersionMapper.toEntity(versionDto, entity);
                        version.setTestCase(entity); // back-reference
                        return version;
                    })
                    .collect(Collectors.toList());
            entity.setTestCaseVersions(versions);
        }
        return entity;
    }
}
