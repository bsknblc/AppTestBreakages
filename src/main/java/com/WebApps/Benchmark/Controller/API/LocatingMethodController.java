package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.DTO.LocatingMethodDTO;
import com.WebApps.Benchmark.Service.BreakageService;
import com.WebApps.Benchmark.Service.LocatingMethodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locating_methods")
public class LocatingMethodController {

    private final LocatingMethodService locatingMethodService;
    private final BreakageService breakageService;
    public LocatingMethodController(LocatingMethodService locatingMethodService, BreakageService breakageService) {
        this.locatingMethodService = locatingMethodService;
        this.breakageService = breakageService;
    }

    @GetMapping
    public ResponseEntity<List<LocatingMethodDTO>> findAll(){
        return ResponseEntity.ok(locatingMethodService.findAll());
    }

    @GetMapping("/{locating_method-id}")
    public ResponseEntity<LocatingMethodDTO> getLocatingMethodById(@PathVariable("locating_method-id") int id) {
        return ResponseEntity.ok(locatingMethodService.findById(id));
    }

    @GetMapping("/{locating_method-id}/breakages")
    public ResponseEntity<List<BreakageDTO>> getBreakageByLocatingMethodId(@PathVariable("locating_method-id") int id) {
        return ResponseEntity.ok(breakageService.findByLocatingMethodId(id));
    }

    @PostMapping
    public ResponseEntity<LocatingMethodDTO> save(@RequestBody LocatingMethodDTO locatingMethod) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locatingMethodService.save(locatingMethod));
    }

}