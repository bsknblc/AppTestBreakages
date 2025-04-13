package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.AppPageDTO;
import com.WebApps.Benchmark.DTO.AppReleaseDTO;
import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.Model.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationMapper {

    public static ApplicationDTO toDTO(Application entity) {
        if (entity == null) return null;

        List<AppPageDTO> appPageDTOs = entity.getPages() != null
                ? entity.getPages().stream()
                .map(AppPageMapper::toDTO)
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
                appPageDTOs
        );
    }

}
