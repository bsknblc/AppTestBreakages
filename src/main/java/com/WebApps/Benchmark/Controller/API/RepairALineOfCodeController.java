package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.RepairALineOfCodeDTO;
import com.WebApps.Benchmark.Service.RepairALineOfCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repair_a_line_of_codes")
public class RepairALineOfCodeController {

    private final RepairALineOfCodeService repairALineOfCodeService;
    public RepairALineOfCodeController(RepairALineOfCodeService repairALineOfCodeService) {
        this.repairALineOfCodeService = repairALineOfCodeService;
    }

    @GetMapping
    public ResponseEntity<List<RepairALineOfCodeDTO>> findAll(){
        return ResponseEntity.ok(repairALineOfCodeService.findAll());
    }

    @GetMapping("/{repair-a-line-of-code-id}")
    public ResponseEntity<RepairALineOfCodeDTO> getRepairALineOfCodeById(@PathVariable("repair-a-line-of-code-id") int id) {
        return ResponseEntity.ok(repairALineOfCodeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RepairALineOfCodeDTO> save(@RequestBody RepairALineOfCodeDTO repairALineOfCodeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repairALineOfCodeService.save(repairALineOfCodeDTO));
    }

}
