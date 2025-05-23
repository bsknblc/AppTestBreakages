package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.BreakageExplanationDTO;
import com.WebApps.Benchmark.DTO.LanguageDTO;
import com.WebApps.Benchmark.Service.BreakageExplanationService;
import com.WebApps.Benchmark.Service.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breakage_explanations")
public class BreakageExplanationController {

    private final BreakageExplanationService breakageExplanationService;
    public BreakageExplanationController(BreakageExplanationService breakageExplanationService) {
        this.breakageExplanationService = breakageExplanationService;
    }

    @GetMapping
    public ResponseEntity<List<BreakageExplanationDTO>> findAll(){
        return ResponseEntity.ok(breakageExplanationService.findAll());
    }

    @GetMapping("/{breakage_explanations-id}")
    public ResponseEntity<BreakageExplanationDTO> getBreakageExplanationById(@PathVariable("breakage_explanations-id") int id) {
        return ResponseEntity.ok(breakageExplanationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BreakageExplanationDTO> save(@RequestBody BreakageExplanationDTO breakageExplanationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(breakageExplanationService.save(breakageExplanationDTO));
    }

}
