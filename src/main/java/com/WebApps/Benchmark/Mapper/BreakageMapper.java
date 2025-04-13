package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.DTO.LineOfCodeDTO;
import com.WebApps.Benchmark.DTO.RepairALineOfCodeDTO;
import com.WebApps.Benchmark.DTO.TestCaseVersionDTO;
import com.WebApps.Benchmark.Model.*;
import com.WebApps.Benchmark.Repository.AppReleaseRepository;
import com.WebApps.Benchmark.Repository.LineOfCodeRepository;
import com.WebApps.Benchmark.Repository.RepairALineOfCodeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BreakageMapper {

    public static BreakageDTO toDTO(Breakage entity) {
        if (entity == null) return null;

        List<RepairALineOfCodeDTO> repairALineOfCodeDTOs = entity.getRepairALineOfCodes() != null
                ? entity.getRepairALineOfCodes().stream()
                .map(RepairALineOfCodeMapper::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return new BreakageDTO(
                entity.getId(),
                entity.getAppRelease() != null ? entity.getAppRelease().getId() : 0,
                entity.getTestCaseVersion() != null ? entity.getTestCaseVersion().getId() : 0,
                repairALineOfCodeDTOs,
                entity.getTaxonomyDescription()
        );
    }

}
