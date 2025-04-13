package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.AppPageDTO;
import com.WebApps.Benchmark.Mapper.AppPageMapper;
import com.WebApps.Benchmark.Model.AppPage;
import com.WebApps.Benchmark.Repository.AppPageRepository;
import com.WebApps.Benchmark.Repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppPageService {

    private final AppPageRepository appPageRepository;
    private final ApplicationRepository applicationRepository;
    public AppPageService(AppPageRepository appPageRepository, ApplicationRepository applicationRepository) {
        this.appPageRepository = appPageRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<AppPageDTO> findAll(){
        List<AppPage> appPages = appPageRepository.findAll();
        return appPages.stream()
                .map(AppPageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AppPageDTO findById(int id){
        AppPage appPage = appPageRepository.getReferenceById(id);
        return AppPageMapper.toDTO(appPage);
    }

    public AppPageDTO save(AppPageDTO appPageDTO) {
        AppPage appPage = new AppPage();
        appPage.setApplication(applicationRepository.getReferenceById(appPageDTO.getApplication()));
        appPage.setPageName(appPageDTO.getPageName());
        appPageRepository.save(appPage);

        appPageDTO.setId(appPage.getId());
        return appPageDTO;
    }

}
