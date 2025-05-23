package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.RepairExplanationDTO;
import com.WebApps.Benchmark.Model.RepairExplanation;

public class RepairExplanationMapper {

    public static RepairExplanationDTO toDTO(RepairExplanation entity) {
        if (entity == null) return null;

        return new RepairExplanationDTO(
                entity.getId(),
                entity.getExplanation()
        );
    }

}
