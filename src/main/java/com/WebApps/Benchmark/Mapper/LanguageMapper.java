package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.DTO.LanguageDTO;
import com.WebApps.Benchmark.DTO.LocatingMethodDTO;
import com.WebApps.Benchmark.Model.Language;
import com.WebApps.Benchmark.Model.LocatingMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageMapper {
    public static LanguageDTO toDTO(Language entity) {
        if (entity == null) return null;

        return new LanguageDTO(
                entity.getId(),
                entity.getLanguageName()
        );
    }
}
