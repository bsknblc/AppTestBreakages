package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.TestCaseDTO;
import com.WebApps.Benchmark.Model.TestCase;
import com.WebApps.Benchmark.Service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<TestCaseDTO> testCaseDTOs = testCaseService.findAll();
        return ResponseEntity.ok(testCaseDTOs);
    }

    @GetMapping("/{test-case-id}")
    public ResponseEntity<TestCaseDTO> getTestCaseById(@PathVariable("test-case-id") int id) {
        TestCaseDTO testCaseDTO = testCaseService.findById(id);
        return ResponseEntity.ok(testCaseDTO);
    }

    @PostMapping
    public ResponseEntity<TestCaseDTO> save(@RequestBody TestCaseDTO testCase) {
        TestCaseDTO testCaseDTO = testCaseService.save(testCase);
        return ResponseEntity.status(HttpStatus.CREATED).body(testCaseDTO);
    }

}
