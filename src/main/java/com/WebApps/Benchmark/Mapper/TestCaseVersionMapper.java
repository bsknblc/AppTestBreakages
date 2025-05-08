package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.DTO.TestCaseVersionDTO;
import com.WebApps.Benchmark.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestCaseVersionMapper {


    public static TestCaseVersionDTO toDTO(TestCaseVersion entity) {
        if (entity == null) return null;


        List<BreakageDTO> breakageDTOs = entity.getBreakages() != null
                ? entity.getBreakages().stream()
                .map(BreakageMapper::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return new TestCaseVersionDTO(
                entity.getId(),
                entity.getTestCaseVersionName(),
                entity.getTestCase() != null ? entity.getTestCase().getId() : 0,
                breakageDTOs
        );
    }

}
