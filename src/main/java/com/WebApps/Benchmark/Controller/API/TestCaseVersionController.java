package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.TestCaseVersionDTO;
import com.WebApps.Benchmark.Service.TestCaseVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test_case_versions")
public class TestCaseVersionController {
    @Autowired
    TestCaseVersionService testCaseVersionService;

    @GetMapping
    public ResponseEntity<List<TestCaseVersionDTO>> findAll(){
        return ResponseEntity.ok(testCaseVersionService.findAll());
    }

    @GetMapping("/{test-case-version-id}")
    public ResponseEntity<TestCaseVersionDTO> getTestCaseVersionById(@PathVariable("test-case-version-id") int id) {
        return ResponseEntity.ok(testCaseVersionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TestCaseVersionDTO> save(@RequestBody TestCaseVersionDTO testCaseVersion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testCaseVersionService.save(testCaseVersion));
    }

}
