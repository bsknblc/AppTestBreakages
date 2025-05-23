package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.DTO.BreakageExplanationDTO;
import com.WebApps.Benchmark.DTO.RepairDTO;
import com.WebApps.Benchmark.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BreakageMapper {

    public static BreakageDTO toDTO(Breakage entity) {
        if (entity == null) return null;

        List<RepairDTO> repairDTOS = entity.getRepairs() != null
                ? entity.getRepairs().stream()
                .map(RepairMapper::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();

        List<BreakageExplanationDTO> breakageExplanationDTOS = entity.getBreakageExplanations() != null
                ? entity.getBreakageExplanations().stream()
                .map(BreakageExplanationMapper::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return new BreakageDTO(
                entity.getId(),
                entity.getAppRelease() != null ? entity.getAppRelease().getId() : 0,
                entity.getTestCaseVersion() != null ? entity.getTestCaseVersion().getId() : 0,
                entity.getBreakageReason() != null ? entity.getBreakageReason().getId() : 0,
                entity.getLocatingMethod() != null ? entity.getLocatingMethod().getId() : 0,
                repairDTOS,
                entity.getDescription(),
                entity.getLine(),
                breakageExplanationDTOS
        );
    }

}
