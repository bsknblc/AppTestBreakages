package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.RepairDTO;
import com.WebApps.Benchmark.DTO.RepairExplanationDTO;
import com.WebApps.Benchmark.Model.Repair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepairMapper {

    public static RepairDTO toDTO(Repair entity) {
        if (entity == null) return null;

        List<RepairExplanationDTO> repairExplanationDTOS = entity.getRepairExplanations() != null
                ? entity.getRepairExplanations().stream()
                .map(RepairExplanationMapper::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return new RepairDTO(
                entity.getId(),
                entity.getBreakage() != null ? entity.getBreakage().getId() : 0,
                entity.getCommitHash(),
                repairExplanationDTOS
        );
    }

}
