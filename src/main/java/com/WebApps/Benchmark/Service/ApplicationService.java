package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.Mapper.ApplicationMapper;
import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
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
        applicationRepository.save(application);

        applicationDTO.setId(application.getId());
        return applicationDTO;
    }


}
