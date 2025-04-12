package com.WebApps.Benchmark.Mapper;

import com.WebApps.Benchmark.DTO.AppPageDTO;
import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.Model.AppPage;
import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Model.TestSuite;

public class AppPageMapper {

    public static AppPageDTO toDTO(AppPage entity) {
        return new AppPageDTO();
    }

    public static AppPage toEntity(AppPageDTO dto, Application parentApplication) {
        return new AppPage();
    }

}
