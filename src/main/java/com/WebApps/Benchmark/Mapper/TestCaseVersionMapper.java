package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.TestCaseDTO;
import com.WebApps.Benchmark.DTO.TestCaseVersionDTO;
import com.WebApps.Benchmark.Model.TestCase;
import com.WebApps.Benchmark.Model.TestCaseVersion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestCaseVersionMapper {

    public static TestCaseVersionDTO toDTO(TestCaseVersion entity) {
        if (entity == null) return null;

/*
        List<TestCaseVersionDTO> versionDTOs = entity.getTestCaseVersions() != null
                ? entity.getTestCaseVersions().stream()
                .map(TestCaseVersionMapper::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();
*/

        return new TestCaseVersionDTO(
                entity.getId(),
                entity.getTestCaseVersionName(),
                entity.getTestCase() != null ? entity.getTestCase().getId() : 0//,
                //versionDTOs
        );
    }

    public static TestCaseVersion toEntity(TestCaseVersionDTO dto, TestCase parentTestCase) {
        if (dto == null) return null;

        TestCaseVersion entity = new TestCaseVersion();
        entity.setTestCase(parentTestCase);
        entity.setTestCaseVersionName(dto.getTestCaseVersionName());


/*        if (dto.getBreakages() != null) {
            List<TestCaseVersion> versions = dto.getTestCaseVersions().stream()
                    .map(versionDto -> {
                        TestCaseVersion version = TestCaseVersionMapper.toEntity(versionDto, entity);
                        version.setTestCase(entity); // back-reference
                        return version;
                    })
                    .collect(Collectors.toList());
            entity.setTestCaseVersions(versions);
        }*/
        return entity;
    }

}
