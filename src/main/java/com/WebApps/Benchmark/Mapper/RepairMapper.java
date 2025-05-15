package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.RepairDTO;
import com.WebApps.Benchmark.Model.Repair;

public class RepairMapper {

    public static RepairDTO toDTO(Repair entity) {
        if (entity == null) return null;

        return new RepairDTO(
                entity.getId(),
                entity.getBreakage() != null ? entity.getBreakage().getId() : 0,
                entity.getCommitHash()
        );
    }

}
