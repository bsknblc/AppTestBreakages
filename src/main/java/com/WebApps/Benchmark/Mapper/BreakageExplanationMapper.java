package com.WebApps.Benchmark.Mapper;


import com.WebApps.Benchmark.DTO.BreakageExplanationDTO;
import com.WebApps.Benchmark.Model.BreakageExplanation;

public class BreakageExplanationMapper {

    public static BreakageExplanationDTO toDTO(BreakageExplanation entity) {
        if (entity == null) return null;

        return new BreakageExplanationDTO(
                entity.getId(),
                entity.getExplanation()
        );
    }

}
