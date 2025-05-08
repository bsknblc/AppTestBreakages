package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.LocatingMethodDTO;
import com.WebApps.Benchmark.Service.LocatingMethodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locating_methods")
public class LocatingMethodController {

    private final LocatingMethodService locatingMethodService;
    public LocatingMethodController(LocatingMethodService locatingMethodService) {
        this.locatingMethodService = locatingMethodService;
    }

    @GetMapping
    public ResponseEntity<List<LocatingMethodDTO>> findAll(){
        return ResponseEntity.ok(locatingMethodService.findAll());
    }

    @GetMapping("/{locating_method-id}")
    public ResponseEntity<LocatingMethodDTO> getLocatingMethodById(@PathVariable("locating_method-id") int id) {
        return ResponseEntity.ok(locatingMethodService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LocatingMethodDTO> save(@RequestBody LocatingMethodDTO locatingMethod) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locatingMethodService.save(locatingMethod));
    }

}