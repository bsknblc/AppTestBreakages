package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.AppReleaseDTO;
import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.Model.AppRelease;
import com.WebApps.Benchmark.Model.Application;

public class AppReleaseMapper {

    public static AppReleaseDTO toDTO(AppRelease entity) {
        return new AppReleaseDTO();
    }

    public static AppRelease toEntity(AppReleaseDTO dto, Application parentApplication) {
        return new AppRelease();
    }

}
