package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.AppReleaseDTO;
import com.WebApps.Benchmark.Service.AppReleaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app_releases")
public class AppReleaseController {

    private final AppReleaseService appReleaseService;
    public AppReleaseController(AppReleaseService appReleaseService) {
        this.appReleaseService = appReleaseService;
    }


    @GetMapping
    public ResponseEntity<List<AppReleaseDTO>> findAll(){
        return ResponseEntity.ok(appReleaseService.findAll());
    }

    @GetMapping("/{app-release-id}")
    public ResponseEntity<AppReleaseDTO> getAppReleaseById(@PathVariable("app-release-id") int id) {
        return ResponseEntity.ok(appReleaseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AppReleaseDTO> save(@RequestBody AppReleaseDTO appReleaseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appReleaseService.save(appReleaseDTO));
    }

}
