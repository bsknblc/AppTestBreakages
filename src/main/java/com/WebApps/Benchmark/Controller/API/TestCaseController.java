package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.TestCaseDTO;
import com.WebApps.Benchmark.Service.TestCaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test_cases")
public class TestCaseController {

    private final TestCaseService testCaseService;
    public TestCaseController(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    @GetMapping
    public ResponseEntity<List<TestCaseDTO>> findAll(){
        return ResponseEntity.ok(testCaseService.findAll());
    }

    @GetMapping("/{test-case-id}")
    public ResponseEntity<TestCaseDTO> getTestCaseById(@PathVariable("test-case-id") int id) {
        return ResponseEntity.ok(testCaseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TestCaseDTO> save(@RequestBody TestCaseDTO testCase) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testCaseService.save(testCase));
    }

}
