package com.WebApps.Benchmark.Controller.API;


import com.WebApps.Benchmark.DTO.TestSuiteDTO;
import com.WebApps.Benchmark.Model.TestSuite;
import com.WebApps.Benchmark.Service.TestSuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test_suites")
public class TestSuiteController {
    @Autowired
    TestSuiteService testSuiteService;

    @GetMapping
    public ResponseEntity<List<TestSuiteDTO>> findAll(){
        List<TestSuiteDTO> testSuiteDTOS = testSuiteService.findAll();
        return ResponseEntity.ok(testSuiteDTOS);
    }

    @GetMapping("/{test-suite-id}")
    public ResponseEntity<TestSuiteDTO> getApplicationById(@PathVariable("test-suite-id") int id) {
        TestSuiteDTO testSuiteDTO = testSuiteService.findById(id);
        return ResponseEntity.ok(testSuiteDTO);
    }

    @PostMapping
    public ResponseEntity<TestSuiteDTO> save(@RequestBody TestSuite testSuite) {
        TestSuiteDTO testSuiteDTO = testSuiteService.save(testSuite);
        return ResponseEntity.status(HttpStatus.CREATED).body(testSuiteDTO);
    }

}
