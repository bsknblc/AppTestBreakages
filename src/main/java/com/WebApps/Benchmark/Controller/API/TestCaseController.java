package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.TestCaseDTO;
import com.WebApps.Benchmark.DTO.TestCaseVersionDTO;
import com.WebApps.Benchmark.Service.TestCaseService;
import com.WebApps.Benchmark.Service.TestCaseVersionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test_cases")
public class TestCaseController {

    private final TestCaseService testCaseService;
    private final TestCaseVersionService testCaseVersionService;
    public TestCaseController(TestCaseService testCaseService, TestCaseVersionService testCaseVersionService) {
        this.testCaseService = testCaseService;
        this.testCaseVersionService = testCaseVersionService;
    }

    @GetMapping
    public ResponseEntity<List<TestCaseDTO>> findAll(){
        return ResponseEntity.ok(testCaseService.findAll());
    }

    @GetMapping("/{test-case-id}")
    public ResponseEntity<TestCaseDTO> getTestCaseById(@PathVariable("test-case-id") int id) {
        return ResponseEntity.ok(testCaseService.findById(id));
    }

    @GetMapping("/{test-case-id}/test_case_versions")
    public ResponseEntity<List<TestCaseVersionDTO>> getTestCaseVersionByTestCaseId(@PathVariable("test-case-id") int id) {
        return ResponseEntity.ok(testCaseVersionService.findByTestCaseId(id));
    }

    @PostMapping
    public ResponseEntity<TestCaseDTO> save(@RequestBody TestCaseDTO testCase) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testCaseService.save(testCase));
    }

}
