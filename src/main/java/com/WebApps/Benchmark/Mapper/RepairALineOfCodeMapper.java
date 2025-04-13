package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.RepairALineOfCodeDTO;
import com.WebApps.Benchmark.Model.RepairALineOfCode;

public class RepairALineOfCodeMapper {

    public static RepairALineOfCodeDTO toDTO(RepairALineOfCode entity) {
        if (entity == null) return null;

        return new RepairALineOfCodeDTO(
                entity.getId(),
                entity.getLineOfCode() != null ? entity.getLineOfCode().getId() : 0,
                entity.getBreakage() != null ? entity.getBreakage().getId() : 0
        );
    }

}
