package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.ApplicationDTO;
import com.WebApps.Benchmark.Model.Application;
import com.WebApps.Benchmark.Service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> findAll(){
        List<ApplicationDTO> appDTOs = applicationService.findAll();
        return ResponseEntity.ok(appDTOs);
    }

    @GetMapping("/{app-id}")
    public ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable("app-id") int id) {
        ApplicationDTO appDTO = applicationService.findById(id);
        return ResponseEntity.ok(appDTO);
    }

    @PostMapping
    public ResponseEntity<ApplicationDTO> save(@RequestBody Application application) {
        ApplicationDTO applicationDTO = applicationService.save(application);
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationDTO);
    }

}
