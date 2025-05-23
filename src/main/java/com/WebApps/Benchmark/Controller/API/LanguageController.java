package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.LanguageDTO;
import com.WebApps.Benchmark.Service.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    private final LanguageService languageService;
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public ResponseEntity<List<LanguageDTO>> findAll(){
        return ResponseEntity.ok(languageService.findAll());
    }

    @GetMapping("/{language-id}")
    public ResponseEntity<LanguageDTO> getLanguageById(@PathVariable("language-id") int id) {
        return ResponseEntity.ok(languageService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LanguageDTO> save(@RequestBody LanguageDTO languageDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(languageService.save(languageDTO));
    }

}
