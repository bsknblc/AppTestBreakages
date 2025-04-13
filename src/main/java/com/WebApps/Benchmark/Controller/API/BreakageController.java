package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.Service.BreakageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breakages")
public class BreakageController {
    @Autowired
    BreakageService breakageService;

    @GetMapping
    public ResponseEntity<List<BreakageDTO>> findAll(){
        return ResponseEntity.ok(breakageService.findAll());
    }

    @GetMapping("/{breakage-id}")
    public ResponseEntity<BreakageDTO> getBreakageById(@PathVariable("breakage-id") int id) {
        return ResponseEntity.ok(breakageService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BreakageDTO> save(@RequestBody BreakageDTO breakage) {
        return ResponseEntity.status(HttpStatus.CREATED).body(breakageService.save(breakage));
    }

}
