package com.WebApps.Benchmark.DTO;

import org.springframework.web.multipart.MultipartFile;

public class FileDTO {

    private int id;
    private String fileName;
    private String fileType;
    private MultipartFile file;
    private String base64Data;

    public FileDTO() {}

    public FileDTO(int id, String fileName, String fileType, String base64Data) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.base64Data = base64Data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }
}