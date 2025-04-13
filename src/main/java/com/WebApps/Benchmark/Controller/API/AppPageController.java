package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.AppPageDTO;
import com.WebApps.Benchmark.Service.AppPageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app_pages")
public class AppPageController {

    private final AppPageService appPageService;
    public AppPageController(AppPageService appPageService) {
        this.appPageService = appPageService;
    }

    @GetMapping
    public ResponseEntity<List<AppPageDTO>> findAll(){
        return ResponseEntity.ok(appPageService.findAll());
    }

    @GetMapping("/{app-page-id}")
    public ResponseEntity<AppPageDTO> getAppPageById(@PathVariable("app-page-id") int id) {
        return ResponseEntity.ok(appPageService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AppPageDTO> save(@RequestBody AppPageDTO appPageDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appPageService.save(appPageDTO));
    }

}