package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.AppPageDTO;
import com.WebApps.Benchmark.Model.AppPage;
import com.WebApps.Benchmark.Service.AppPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app_pages")
public class AppPageController {
    @Autowired
    AppPageService appPageService;

    @GetMapping
    public ResponseEntity<List<AppPageDTO>> findAll(){
        List<AppPageDTO> appPageDTOS = appPageService.findAll();
        return ResponseEntity.ok(appPageDTOS);
    }

    @GetMapping("/{app-page-id}")
    public ResponseEntity<AppPageDTO> getAppPageById(@PathVariable("app_page-id") int id) {
        AppPageDTO appPageDTO = appPageService.findById(id);
        return ResponseEntity.ok(appPageDTO);
    }

    @PostMapping
    public ResponseEntity<AppPage> save(@RequestBody AppPage appPage) {
        AppPage createdPage = appPageService.save(appPage);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPage);
    }

}