package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.DTO.LanguageDTO;
import com.WebApps.Benchmark.Mapper.ApplicationMapper;
import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Model.Language;
import com.WebApps.Benchmark.Repository.ApplicationRepository;
import com.WebApps.Benchmark.Repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final LanguageRepository languageRepository;
    public ApplicationService(ApplicationRepository applicationRepository, LanguageRepository languageRepository) {
        this.applicationRepository = applicationRepository;
        this.languageRepository = languageRepository;
    }

    public List<ApplicationDTO> findAll(){
        List<Application> apps = applicationRepository.findAll();
        return apps.stream()
                .map(ApplicationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ApplicationDTO findById(int id){
        Application app = applicationRepository.getReferenceById(id);
        return ApplicationMapper.toDTO(app);
    }

    public ApplicationDTO save(ApplicationDTO applicationDTO) {
        Application application = new Application();
        application.setAppName(applicationDTO.getAppName());
        application.setUrl(applicationDTO.getUrl());
        application.setLanguages(applicationDTO.getLanguages().stream()
                .map(dto -> languageRepository.getReferenceById(dto.getId()))
                .collect(Collectors.toList()));
        applicationRepository.save(application);

        applicationDTO.setId(application.getId());
        return applicationDTO;
    }


}
