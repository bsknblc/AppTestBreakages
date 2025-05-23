package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.RepairDTO;
import com.WebApps.Benchmark.Service.RepairService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repairs")
public class RepairController {

    private final RepairService repairService;
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping
    public ResponseEntity<List<RepairDTO>> findAll(){
        return ResponseEntity.ok(repairService.findAll());
    }

    @GetMapping("/{repair-a-line-of-code-id}")
    public ResponseEntity<RepairDTO> getRepairALineOfCodeById(@PathVariable("repair-a-line-of-code-id") int id) {
        return ResponseEntity.ok(repairService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RepairDTO> save(@RequestBody RepairDTO repairDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repairService.save(repairDTO));
    }

}
