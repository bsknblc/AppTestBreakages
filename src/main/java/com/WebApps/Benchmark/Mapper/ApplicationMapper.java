package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.AppReleaseDTO;
import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.DTO.TestSuiteDTO;
import com.WebApps.Benchmark.Model.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationMapper {

    public static ApplicationDTO toDTO(Application entity) {
        if (entity == null) return null;

        List<TestSuiteDTO> testSuiteDTOS = entity.getTestSuites() != null
                ? entity.getTestSuites().stream()
                .map(TestSuiteMapper::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();

        List<AppReleaseDTO> appReleaseDTOs = entity.getReleases() != null
                ? entity.getReleases().stream()
                .map(AppReleaseMapper::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return new ApplicationDTO(
                entity.getId(),
                entity.getAppName(),
                entity.getUrl(),
                appReleaseDTOs,
                testSuiteDTOS
        );
    }

}
