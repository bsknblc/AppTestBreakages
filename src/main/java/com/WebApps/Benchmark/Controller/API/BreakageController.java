package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.Model.CommitChanges;
import com.WebApps.Benchmark.DTO.BreakageDTO;
import com.WebApps.Benchmark.DTO.RepairDTO;
import com.WebApps.Benchmark.Service.BreakageService;
import com.WebApps.Benchmark.Service.RepairService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breakages")
public class BreakageController {

    private final BreakageService breakageService;
    private final RepairService repairService;
    public BreakageController(BreakageService breakageService, RepairService repairService) {
        this.breakageService = breakageService;
        this.repairService = repairService;
    }

    @GetMapping
    public ResponseEntity<List<BreakageDTO>> findAll(){
        return ResponseEntity.ok(breakageService.findAll());
    }

    @GetMapping("/{breakage-id}")
    public ResponseEntity<BreakageDTO> getBreakageById(@PathVariable("breakage-id") int id) {
        return ResponseEntity.ok(breakageService.findById(id));
    }

    @GetMapping("/{breakage-id}/commit")
    public ResponseEntity<CommitChanges> getCommitChanges(@PathVariable("breakage-id") int id) {
        return ResponseEntity.ok(breakageService.getCommitChanges(id));
    }

    @GetMapping("/{breakage-id}/repairs")
    public ResponseEntity<List<RepairDTO>> getRepairByBreakageId(@PathVariable("breakage-id") int id) {
        return ResponseEntity.ok(repairService.findByBreakageId(id));
    }

    @PostMapping
    public ResponseEntity<BreakageDTO> save(@RequestBody BreakageDTO breakage) {
        return ResponseEntity.status(HttpStatus.CREATED).body(breakageService.save(breakage));
    }

    @PutMapping("/{breakageId}/explanations/{explanationId}")
    public ResponseEntity<BreakageDTO> addExplanation(
            @PathVariable int breakageId,
            @PathVariable int explanationId) {
        return ResponseEntity.ok(breakageService.addBreakageExplanation(breakageId, explanationId));
    }

    @DeleteMapping("/{breakageId}/explanations/{explanationId}")
    public ResponseEntity<BreakageDTO> removeExplanation(
            @PathVariable int breakageId,
            @PathVariable int explanationId) {
        return ResponseEntity.ok(breakageService.deleteBreakageExplanation(breakageId, explanationId));
    }

}
