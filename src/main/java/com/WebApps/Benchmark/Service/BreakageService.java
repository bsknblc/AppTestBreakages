package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.Model.Breakage;
import com.WebApps.Benchmark.Repository.BreakageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BreakageService {
    @Autowired
    BreakageRepository breakageRepository;

    public List<BreakageDTO> findAll(){
        List<Breakage> breakages = breakageRepository.findAll();
        List<BreakageDTO> breakageDTOS = new ArrayList<>();
        for (Breakage breakage: breakages) {
            breakageDTOS.add(new BreakageDTO(breakage.getId(), breakage.getAppRelease(), breakage.getTestCaseVersion(), breakage.getRepairALineOfCodes(), breakage.getTaxonomyDescription()));
        }
        return breakageDTOS;
    }

    public BreakageDTO findById(int id){
        Breakage breakage = breakageRepository.getReferenceById(id);
        return new BreakageDTO(breakage.getId(), breakage.getAppRelease(), breakage.getTestCaseVersion(), breakage.getRepairALineOfCodes(), breakage.getTaxonomyDescription());
    }

    public BreakageDTO save(Breakage breakage1) {
        Breakage breakage = breakageRepository.save(breakage1);
        return new BreakageDTO(breakage.getId(), breakage.getAppRelease(), breakage.getTestCaseVersion(), breakage.getRepairALineOfCodes(), breakage.getTaxonomyDescription());
    }

}
