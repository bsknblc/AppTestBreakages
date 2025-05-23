package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.RepairExplanationDTO;
import com.WebApps.Benchmark.Service.RepairExplanationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repair_explanations")
public class RepairExplanationController {

    private final RepairExplanationService repairExplanationService;
    public RepairExplanationController(RepairExplanationService repairExplanationService) {
        this.repairExplanationService = repairExplanationService;
    }

    @GetMapping
    public ResponseEntity<List<RepairExplanationDTO>> findAll(){
        return ResponseEntity.ok(repairExplanationService.findAll());
    }

    @GetMapping("/{repair_explanations-id}")
    public ResponseEntity<RepairExplanationDTO> getRepairExplanationById(@PathVariable("repair_explanations-id") int id) {
        return ResponseEntity.ok(repairExplanationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RepairExplanationDTO> save(@RequestBody RepairExplanationDTO repairExplanationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repairExplanationService.save(repairExplanationDTO));
    }

}
