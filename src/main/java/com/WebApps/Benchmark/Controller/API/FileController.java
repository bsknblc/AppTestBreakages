package com.WebApps.Benchmark.Controller.API;

import com.WebApps.Benchmark.DTO.FileDTO;
import com.WebApps.Benchmark.Model.File;
import com.WebApps.Benchmark.Service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@ModelAttribute FileDTO fileDto) {
        try {
            File savedFile = fileService.saveFile(fileDto.getFile());
            return ResponseEntity.ok("File uploaded successfully! ID: " + savedFile.getId());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Could not upload the file: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileDTO> downloadFileAsJson(@PathVariable long id) {
        FileDTO dto = fileService.getFile(id);
        return ResponseEntity.ok(dto);
    }

}
