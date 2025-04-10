package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.AppPageDTO;
import com.WebApps.Benchmark.DTO.AppReleaseDTO;
import com.WebApps.Benchmark.Model.AppPage;
import com.WebApps.Benchmark.Model.AppRelease;
import com.WebApps.Benchmark.Service.AppPageService;
import com.WebApps.Benchmark.Service.AppReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app_releases")
public class AppReleaseController {

    @Autowired
    AppReleaseService appReleaseService;


    @GetMapping
    public ResponseEntity<List<AppReleaseDTO>> findAll(){
        List<AppReleaseDTO> appReleaseDTOS = appReleaseService.findAll();
        return ResponseEntity.ok(appReleaseDTOS);
    }

    @GetMapping("/{app-release-id}")
    public ResponseEntity<AppReleaseDTO> getAppReleaseById(@PathVariable("app-release-id") int id) {
        AppReleaseDTO appReleaseDTO = appReleaseService.findById(id);
        return ResponseEntity.ok(appReleaseDTO);
    }

    @PostMapping
    public ResponseEntity<AppRelease> save(@RequestBody AppRelease appRelease) {
        AppRelease release = appReleaseService.save(appRelease);
        return ResponseEntity.status(HttpStatus.CREATED).body(release);
    }


}
