package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.Mapper.BreakageMapper;
import com.WebApps.Benchmark.Model.Breakage;
import com.WebApps.Benchmark.Repository.AppReleaseRepository;
import com.WebApps.Benchmark.Repository.BreakageRepository;
import com.WebApps.Benchmark.Repository.TestCaseVersionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BreakageService {

    private final BreakageRepository breakageRepository;
    private final TestCaseVersionRepository testCaseVersionRepository;
    private final AppReleaseRepository appReleaseRepository;
    public BreakageService(BreakageRepository breakageRepository, TestCaseVersionRepository testCaseVersionRepository, AppReleaseRepository appReleaseRepository) {
        this.breakageRepository = breakageRepository;
        this.testCaseVersionRepository = testCaseVersionRepository;
        this.appReleaseRepository = appReleaseRepository;
    }

    public List<BreakageDTO> findAll(){
        List<Breakage> breakages = breakageRepository.findAll();
        return breakages.stream()
                .map(BreakageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BreakageDTO findById(int id){
        Breakage breakage = breakageRepository.getReferenceById(id);
        return BreakageMapper.toDTO(breakage);
    }

    public BreakageDTO save(BreakageDTO breakageDTO) {
        Breakage breakage = new Breakage();
        breakage.setTaxonomyDescription(breakageDTO.getTaxonomyDescription());
        breakage.setAppRelease(appReleaseRepository.getReferenceById(breakageDTO.getAppRelease()));
        breakage.setTestCaseVersion(testCaseVersionRepository.getReferenceById(breakageDTO.getTestCaseVersion()));
        breakageRepository.save(breakage);

        breakageDTO.setId(breakage.getId());
        return breakageDTO;
    }

}
