package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.DTO.TestCaseVersionDTO;
import com.WebApps.Benchmark.Service.BreakageService;
import com.WebApps.Benchmark.Service.TestCaseVersionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test_case_versions")
public class TestCaseVersionController {

    private final TestCaseVersionService testCaseVersionService;
    private final BreakageService breakageService;
    public TestCaseVersionController(TestCaseVersionService testCaseVersionService, BreakageService breakageService) {
        this.testCaseVersionService = testCaseVersionService;
        this.breakageService = breakageService;
    }

    @GetMapping
    public ResponseEntity<List<TestCaseVersionDTO>> findAll(){
        return ResponseEntity.ok(testCaseVersionService.findAll());
    }

    @GetMapping("/{test-case-version-id}")
    public ResponseEntity<TestCaseVersionDTO> getTestCaseVersionById(@PathVariable("test-case-version-id") int id) {
        return ResponseEntity.ok(testCaseVersionService.findById(id));
    }

    @GetMapping("/{test-case-version-id}/breakages")
    public ResponseEntity<List<BreakageDTO>> getBreakageByTestCaseVersionId(@PathVariable("test-case-version-id") int id) {
        return ResponseEntity.ok(breakageService.findByTestCaseVersionId(id));
    }

    @PostMapping
    public ResponseEntity<TestCaseVersionDTO> save(@RequestBody TestCaseVersionDTO testCaseVersion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testCaseVersionService.save(testCaseVersion));
    }

}
