package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.ExplanationStats;
import com.WebApps.Benchmark.Model.BreakageExplanation;
import com.WebApps.Benchmark.Model.CommitChanges;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.Mapper.BreakageMapper;
import com.WebApps.Benchmark.Model.Breakage;
import com.WebApps.Benchmark.Model.Repair;
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


    public CommitChanges getCommitChanges(int breakageId) {
        Breakage breakage = breakageRepository.getReferenceById(breakageId);
        List<Repair> repairs = breakage.getRepairs();
        String githubUsername = "bsknblc";
        String githubToken = "token";

        if (repairs.isEmpty()) {
            return null;
        }

        // Just using the first repair for example
        Repair repair = repairs.get(0);
        String commitHash = repair.getCommitHash();
        String repositoryURL = breakage.getTestCaseVersion().getTestCase().getTestSuite().getUrl();

        // Extract owner and repo from URL
        // Example: https://github.com/owner/repo -> owner/repo
        String[] parts = repositoryURL.replace("https://github.com/", "").split("/");
        String owner = parts[0];
        String repo = parts[1];

        String apiUrl = String.format("https://api.github.com/repos/%s/%s/commits/%s", owner, repo, commitHash);

        // Create headers with authentication
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(githubUsername, githubToken);
        headers.set("Accept", "application/vnd.github.v3+json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CommitChanges> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                CommitChanges.class
        );

        return response.getBody();
    }

    public BreakageDTO addBreakageExplanation(int breakageId, int explanationId) {
        Breakage breakage = breakageRepository.getReferenceById(breakageId);
        BreakageExplanation breakageExplanation = breakageExplanationRepository.getReferenceById(explanationId);
        if (!breakage.getBreakageExplanations().contains(breakageExplanation)) {
            breakage.getBreakageExplanations().add(breakageExplanation);
            breakageRepository.save(breakage);
        }
        return BreakageMapper.toDTO(breakage);
    }

    public BreakageDTO deleteBreakageExplanation(int breakageId, int explanationId) {
        Breakage breakage = breakageRepository.getReferenceById(breakageId);
        BreakageExplanation breakageExplanation = breakageExplanationRepository.getReferenceById(explanationId);
        breakage.getBreakageExplanations().remove(breakageExplanation);
        breakageRepository.save(breakage);
        return BreakageMapper.toDTO(breakage);
    }

    public List<BreakageDTO> getBreakagesByExplanations(List<String> explanations) {
        List<Breakage> breakages = breakageRepository.findByBreakageExplanationTexts(explanations);
        return breakages.stream()
                .map(BreakageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ExplanationStats> getBreakageExplanationStats() {
        return breakageRepository.countBreakagesGroupedByExplanation();
    }

    public List<ExplanationStats> getBreakageExplanationStatsWithoutValidation() {
        return breakageRepository.countBreakagesGroupedByExplanationWithoutValidation();
    }

}
