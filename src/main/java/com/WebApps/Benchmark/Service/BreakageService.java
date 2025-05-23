package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.Mapper.BreakageMapper;
import com.WebApps.Benchmark.Model.Breakage;
import com.WebApps.Benchmark.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BreakageService {

    private final BreakageRepository breakageRepository;
    private final TestCaseVersionRepository testCaseVersionRepository;
    private final AppReleaseRepository appReleaseRepository;
    private final BreakageReasonRepository breakageReasonRepository;
    private final LocatingMethodRepository locatingMethodRepository;
    private final BreakageExplanationRepository breakageExplanationRepository;
    public BreakageService(BreakageRepository breakageRepository, TestCaseVersionRepository testCaseVersionRepository, AppReleaseRepository appReleaseRepository, BreakageReasonRepository breakageReasonRepository, LocatingMethodRepository locatingMethodRepository, BreakageExplanationRepository breakageExplanationRepository) {
        this.breakageRepository = breakageRepository;
        this.testCaseVersionRepository = testCaseVersionRepository;
        this.appReleaseRepository = appReleaseRepository;
        this.breakageReasonRepository = breakageReasonRepository;
        this.locatingMethodRepository = locatingMethodRepository;
        this.breakageExplanationRepository = breakageExplanationRepository;
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

    public List<BreakageDTO> findByTestCaseVersionId(int testCaseVersionId){
        List<Breakage> breakages = breakageRepository.findByTestCaseVersion_Id(testCaseVersionId);
        return breakages.stream()
                .map(BreakageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<BreakageDTO> findByBreakageReasonId(int breakageReasonId){
        List<Breakage> breakages = breakageRepository.findByBreakageReason_Id(breakageReasonId);
        return breakages.stream()
                .map(BreakageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<BreakageDTO> findByLocatingMethodId(int locatingMethodId){
        List<Breakage> breakages = breakageRepository.findByLocatingMethod_Id(locatingMethodId);
        return breakages.stream()
                .map(BreakageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BreakageDTO save(BreakageDTO breakageDTO) {
        Breakage breakage = new Breakage();
        breakage.setDescription(breakageDTO.getDescription());
        breakage.setLine(breakageDTO.getLine());
        breakage.setAppRelease(appReleaseRepository.getReferenceById(breakageDTO.getAppReleaseId()));
        breakage.setTestCaseVersion(testCaseVersionRepository.getReferenceById(breakageDTO.getTestCaseVersionId()));
        breakage.setBreakageReason(breakageReasonRepository.getReferenceById(breakageDTO.getBreakageReasonId()));
        if (breakageDTO.getLocatingMethodId() != 0) {
            breakage.setLocatingMethod(locatingMethodRepository.getReferenceById(breakageDTO.getLocatingMethodId()));
        }
        breakage.setBreakageExplanations(breakageDTO.getBreakageExplanations().stream()
                .map(dto -> breakageExplanationRepository.getReferenceById(dto.getId()))
                .collect(Collectors.toList()));
        breakageRepository.save(breakage);

        breakageDTO.setId(breakage.getId());
        return breakageDTO;
    }

}
