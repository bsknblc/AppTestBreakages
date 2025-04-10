package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.Model.AppPage;
import com.WebApps.Benchmark.Model.AppRelease;
import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;

    public List<ApplicationDTO> findAll(){
        List<Application> apps = applicationRepository.findAll();
        List<ApplicationDTO> appDTOs = new ArrayList<>();
        for (Application app: apps) {
            appDTOs.add(new ApplicationDTO(app.getId(), app.getAppName(), app.getUrl(), app.getReleases(), app.getPages()));
        }
        return appDTOs;
    }

    public ApplicationDTO findById(int id){
        Application app = applicationRepository.getReferenceById(id);
        return new ApplicationDTO(app.getId(), app.getAppName(), app.getUrl(), app.getReleases(), app.getPages());
    }

    public ApplicationDTO save(Application application) {
        Application app = applicationRepository.save(application);
        return new ApplicationDTO(app.getId(), app.getAppName(), app.getUrl(), app.getReleases(), app.getPages());
    }


}
