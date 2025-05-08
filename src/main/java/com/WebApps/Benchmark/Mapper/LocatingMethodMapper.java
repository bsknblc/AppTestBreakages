package com.WebApps.Benchmark.Mapper;


import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.DTO.LocatingMethodDTO;
import com.WebApps.Benchmark.Model.LocatingMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocatingMethodMapper {

    public static LocatingMethodDTO toDTO(LocatingMethod entity) {
        if (entity == null) return null;

        List<BreakageDTO> breakageDTOS = entity.getBreakages() != null
                ? entity.getBreakages().stream()
                .map(BreakageMapper::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return new LocatingMethodDTO(
                entity.getId(),
                entity.getLocatingMethodName(),
                breakageDTOS
        );
    }
}
