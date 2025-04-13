package com.WebApps.Benchmark.Controller.API;


import com.WebApps.Benchmark.DTO.TestSuiteDTO;
import com.WebApps.Benchmark.Service.TestSuiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test_suites")
public class TestSuiteController {

    private final TestSuiteService testSuiteService;
    public TestSuiteController(TestSuiteService testSuiteService) {
        this.testSuiteService = testSuiteService;
    }

    @GetMapping
    public ResponseEntity<List<TestSuiteDTO>> findAll(){
        return ResponseEntity.ok(testSuiteService.findAll());
    }

    @GetMapping("/{test-suite-id}")
    public ResponseEntity<TestSuiteDTO> getTestSuiteById(@PathVariable("test-suite-id") int id) {
        return ResponseEntity.ok(testSuiteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TestSuiteDTO> save(@RequestBody TestSuiteDTO testSuite) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testSuiteService.save(testSuite));
    }

}
