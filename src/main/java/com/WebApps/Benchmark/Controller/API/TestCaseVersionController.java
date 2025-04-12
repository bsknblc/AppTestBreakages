package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.DTO.TestCaseVersionDTO;
import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Model.TestCaseVersion;
import com.WebApps.Benchmark.Service.ApplicationService;
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
        List<TestCaseVersionDTO> testCaseVersionDTOs = testCaseVersionService.findAll();
        return ResponseEntity.ok(testCaseVersionDTOs);
    }

    @GetMapping("/{test-case-version-id}")
    public ResponseEntity<TestCaseVersionDTO> getTestCaseVersionById(@PathVariable("test-case-version-id") int id) {
        TestCaseVersionDTO testCaseVersionDTO = testCaseVersionService.findById(id);
        return ResponseEntity.ok(testCaseVersionDTO);
    }

    @PostMapping
    public ResponseEntity<TestCaseVersionDTO> save(@RequestBody TestCaseVersion testCaseVersion) {
        TestCaseVersionDTO testCaseVersionDTO = testCaseVersionService.save(testCaseVersion);
        return ResponseEntity.status(HttpStatus.CREATED).body(testCaseVersionDTO);
    }

}
