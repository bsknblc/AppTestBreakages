package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Model.Breakage;
import com.WebApps.Benchmark.Service.ApplicationService;
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
        List<BreakageDTO> breakageDTOs = breakageService.findAll();
        return ResponseEntity.ok(breakageDTOs);
    }

    @GetMapping("/{breakage-id}")
    public ResponseEntity<BreakageDTO> getBreakageById(@PathVariable("breakage-id") int id) {
        BreakageDTO breakageDTO = breakageService.findById(id);
        return ResponseEntity.ok(breakageDTO);
    }

    @PostMapping
    public ResponseEntity<BreakageDTO> save(@RequestBody Breakage breakage) {
        BreakageDTO breakageDTO = breakageService.save(breakage);
        return ResponseEntity.status(HttpStatus.CREATED).body(breakageDTO);
    }

}
