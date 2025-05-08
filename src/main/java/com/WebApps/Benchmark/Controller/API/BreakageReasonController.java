package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.BreakageReasonDTO;
import com.WebApps.Benchmark.Service.BreakageReasonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breakage_reasons")
public class BreakageReasonController {

    private final BreakageReasonService breakageReasonService;

    public BreakageReasonController(BreakageReasonService breakageReasonService) {
        this.breakageReasonService = breakageReasonService;
    }

    @GetMapping
    public ResponseEntity<List<BreakageReasonDTO>> findAll() {
        return ResponseEntity.ok(breakageReasonService.findAll());
    }

    @GetMapping("/{breakage_reason-id}")
    public ResponseEntity<BreakageReasonDTO> getBreakageReasonById(@PathVariable("breakage_reason-id") int id) {
        return ResponseEntity.ok(breakageReasonService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BreakageReasonDTO> save(@RequestBody BreakageReasonDTO breakageReason) {
        return ResponseEntity.status(HttpStatus.CREATED).body(breakageReasonService.save(breakageReason));
    }

}