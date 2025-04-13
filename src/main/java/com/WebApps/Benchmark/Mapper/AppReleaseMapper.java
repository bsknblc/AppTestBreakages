package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.AppPageDTO;
import com.WebApps.Benchmark.DTO.AppReleaseDTO;
import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.DTO.LineOfCodeDTO;
import com.WebApps.Benchmark.Model.AppPage;
import com.WebApps.Benchmark.Model.AppRelease;
import com.WebApps.Benchmark.Model.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppReleaseMapper {

    public static AppReleaseDTO toDTO(AppRelease entity) {
        if (entity == null) return null;

        List<BreakageDTO> breakages = entity.getBreakages() != null
                ? entity.getBreakages().stream()
                .map(BreakageMapper::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return new AppReleaseDTO(
                entity.getId(),
                entity.getReleaseName(),
                breakages,
                entity.getApplication() != null ? entity.getApplication().getId() : 0
        );
    }
}
