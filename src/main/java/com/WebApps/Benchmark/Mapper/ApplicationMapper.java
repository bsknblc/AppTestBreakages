package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.AppPageDTO;
import com.WebApps.Benchmark.DTO.AppReleaseDTO;
import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.Model.AppPage;
import com.WebApps.Benchmark.Model.AppRelease;
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

    public static Application toEntity(ApplicationDTO dto) {
        if (dto == null) return null;

        Application entity = new Application();
        entity.setAppName(dto.getAppName());
        entity.setUrl(dto.getUrl());

        if (dto.getPages() != null) {
            List<AppPage> appPages = dto.getPages().stream()
                    .map(appPageDTO -> {
                        AppPage appPage = AppPageMapper.toEntity(appPageDTO, entity);
                        appPage.setApplication(entity); // Set back-reference
                        return appPage;
                    })
                    .collect(Collectors.toList());
            entity.setPages(appPages);
        }

        if (dto.getReleases() != null) {
            List<AppRelease> appReleases = dto.getReleases().stream()
                    .map(appReleaseDTO -> {
                        AppRelease appRelease = AppReleaseMapper.toEntity(appReleaseDTO, entity);
                        appRelease.setApplication(entity); // Set back-reference
                        return appRelease;
                    })
                    .collect(Collectors.toList());
            entity.setReleases(appReleases);
        }

        return entity;
    }

}
