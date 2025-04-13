package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.AppReleaseDTO;
import com.WebApps.Benchmark.Mapper.AppReleaseMapper;
import com.WebApps.Benchmark.Model.AppRelease;
import com.WebApps.Benchmark.Repository.AppReleaseRepository;
import com.WebApps.Benchmark.Repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppReleaseService {

    private final AppReleaseRepository appReleaseRepository;
    private final ApplicationRepository applicationRepository;
    public AppReleaseService(AppReleaseRepository appReleaseRepository, ApplicationRepository applicationRepository) {
        this.appReleaseRepository = appReleaseRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<AppReleaseDTO> findAll(){
        List<AppRelease> appReleases = appReleaseRepository.findAll();
        return appReleases.stream()
                .map(AppReleaseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AppReleaseDTO findById(int id){
        AppRelease appRelease = appReleaseRepository.getReferenceById(id);
        return AppReleaseMapper.toDTO(appRelease);
    }

    public AppReleaseDTO save(AppReleaseDTO appReleaseDTO) {
        AppRelease appRelease = new AppRelease();
        appRelease.setReleaseName(appReleaseDTO.getReleaseName());
        appRelease.setApplication(applicationRepository.getReferenceById(appReleaseDTO.getApplicationID()));
        appReleaseRepository.save(appRelease);

        appReleaseDTO.setId(appRelease.getId());
        return appReleaseDTO;
    }

}
