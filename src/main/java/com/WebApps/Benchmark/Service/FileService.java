package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.FileDTO;
import com.WebApps.Benchmark.Model.File;
import com.WebApps.Benchmark.Repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File saveFile(MultipartFile file) throws IOException {
        File entity = new File();
        entity.setFileName(file.getOriginalFilename());
        entity.setFileType(file.getContentType());
        entity.setData(file.getBytes());
        return fileRepository.save(entity);
    }

    public FileDTO getFile(long id) {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id " + id));

        String content;
        String mimeType = file.getFileType();

        if (mimeType != null && mimeType.startsWith("text") || mimeType.endsWith("json") || mimeType.endsWith("xml") || mimeType.endsWith("javascript")) {
            content = new String(file.getData(), StandardCharsets.UTF_8); // ✅ actual content
        } else {
            content = Base64.getEncoder().encodeToString(file.getData()); // fallback
        }

        return new FileDTO(
                file.getId(),
                file.getFileName(),
                file.getFileType(),
                content
        );
    }
}