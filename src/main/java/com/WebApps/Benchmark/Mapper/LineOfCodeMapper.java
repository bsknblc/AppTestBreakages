package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.DTO.LineOfCodeDTO;
import com.WebApps.Benchmark.DTO.RepairALineOfCodeDTO;
import com.WebApps.Benchmark.Model.Breakage;
import com.WebApps.Benchmark.Model.LineOfCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LineOfCodeMapper {

    public static LineOfCodeDTO toDTO(LineOfCode entity) {
        if (entity == null) return null;

        RepairALineOfCodeDTO repairALineOfCodeDTO = entity.getRepairALineOfCode() != null
                ? RepairALineOfCodeMapper.toDTO(entity.getRepairALineOfCode()) : null;

        return new LineOfCodeDTO(
                entity.getId(),
                entity.getAppPage() != null ? entity.getAppPage().getId() : 0,
                entity.getTestCaseVersion() != null ? entity.getTestCaseVersion().getId() : 0,
                repairALineOfCodeDTO
        );
    }

}
