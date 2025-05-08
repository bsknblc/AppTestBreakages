package com.WebApps.Benchmark.Compare;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files")
public class FileComparisonController {

    private final CompareService compareService;

    public FileComparisonController(CompareService compareService) {
        this.compareService = compareService;
    }

    @GetMapping("/diff/{id1}/{id2}")
    public ResponseEntity<String> compareFiles(@PathVariable Long id1, @PathVariable Long id2) {
        String diff = compareService.compareFiles(id1, id2);
        return ResponseEntity.ok(diff);
    }
}