package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.LineOfCodeDTO;
import com.WebApps.Benchmark.Service.LineOfCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/line_of_codes")
public class LineOfCodeController {

    private final LineOfCodeService lineOfCodeService;
    public LineOfCodeController(LineOfCodeService lineOfCodeService) {
        this.lineOfCodeService = lineOfCodeService;
    }

    @GetMapping
    public ResponseEntity<List<LineOfCodeDTO>> findAll(){
        return ResponseEntity.ok(lineOfCodeService.findAll());
    }

    @GetMapping("/{line-of-code-id}")
    public ResponseEntity<LineOfCodeDTO> getLineOfCodeById(@PathVariable("line-of-code-id") int id) {
        return ResponseEntity.ok(lineOfCodeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LineOfCodeDTO> save(@RequestBody LineOfCodeDTO lineOfCodeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lineOfCodeService.save(lineOfCodeDTO));
    }

}
