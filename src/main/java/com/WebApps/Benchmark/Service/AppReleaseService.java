package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.AppReleaseDTO;
import com.WebApps.Benchmark.Model.AppRelease;
import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Repository.AppReleaseRepository;
import com.WebApps.Benchmark.Repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppReleaseService {
    @Autowired
    AppReleaseRepository appReleaseRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    public List<AppReleaseDTO> findAll(){
        List<AppRelease> appReleases = appReleaseRepository.findAll();
        List<AppReleaseDTO> appReleaseDTOs = new ArrayList<>();

        return appReleaseDTOs;
    }

    public AppReleaseDTO findById(int id){
        AppRelease appRelease = appReleaseRepository.getReferenceById(id);
        return new AppReleaseDTO();
    }

    public AppRelease save(AppRelease appRelease) {
        return appRelease;
    }


}
