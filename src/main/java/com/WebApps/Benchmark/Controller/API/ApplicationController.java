package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.Service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> findAll(){
        return ResponseEntity.ok(applicationService.findAll());
    }

    @GetMapping("/{app-id}")
    public ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable("app-id") int id) {
        return ResponseEntity.ok(applicationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ApplicationDTO> save(@RequestBody ApplicationDTO application) {
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.save(application));
    }

}
