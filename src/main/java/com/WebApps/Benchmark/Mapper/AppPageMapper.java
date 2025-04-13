package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.*;
import com.WebApps.Benchmark.Model.AppPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppPageMapper {

    public static AppPageDTO toDTO(AppPage entity) {
        if (entity == null) return null;

        List<LineOfCodeDTO> lineOfCodeDTOs = entity.getLineOfCodes() != null
                ? entity.getLineOfCodes().stream()
                .map(LineOfCodeMapper::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return new AppPageDTO(
                entity.getId(),
                entity.getPageName(),
                entity.getApplication() != null ? entity.getApplication().getId() : 0,
                lineOfCodeDTOs
        );
    }

}
