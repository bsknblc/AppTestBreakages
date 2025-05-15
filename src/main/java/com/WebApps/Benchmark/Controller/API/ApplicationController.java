package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.AppReleaseDTO;
import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.DTO.TestSuiteDTO;
import com.WebApps.Benchmark.Service.AppReleaseService;
import com.WebApps.Benchmark.Service.ApplicationService;
import com.WebApps.Benchmark.Service.TestSuiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final AppReleaseService appReleaseService;
    private final TestSuiteService testSuiteService;
    public ApplicationController(ApplicationService applicationService, AppReleaseService appReleaseService, TestSuiteService testSuiteService) {
        this.applicationService = applicationService;
        this.appReleaseService = appReleaseService;
        this.testSuiteService = testSuiteService;
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> findAll(){
        return ResponseEntity.ok(applicationService.findAll());
    }

    @GetMapping("/{app-id}")
    public ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable("app-id") int id) {
        return ResponseEntity.ok(applicationService.findById(id));
    }

    @GetMapping("/{app-id}/app_releases")
    public ResponseEntity<List<AppReleaseDTO>> getAppReleasesByApplicationId(@PathVariable("app-id") int id) {
        return ResponseEntity.ok(appReleaseService.findByApplication_Id(id));
    }

    @GetMapping("/{app-id}/test_suites")
    public ResponseEntity<List<TestSuiteDTO>> getTestSuitesByApplicationId(@PathVariable("app-id") int id) {
        return ResponseEntity.ok(testSuiteService.findByApplication_Id(id));
    }

    @PostMapping
    public ResponseEntity<ApplicationDTO> save(@RequestBody ApplicationDTO application) {
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.save(application));
    }

}
