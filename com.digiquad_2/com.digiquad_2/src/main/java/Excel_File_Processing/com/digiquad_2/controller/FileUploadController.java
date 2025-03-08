package Excel_File_Processing.com.digiquad_2.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import Excel_File_Processing.com.digiquad_2.dto.RowData;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private final Excel_File_Processing.com.digiquad_2.service.FileProcessingService fileProcessingService;

    public FileUploadController(Excel_File_Processing.com.digiquad_2.service.FileProcessingService fileProcessingService) {
        this.fileProcessingService = fileProcessingService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "startRow", defaultValue = "0") int startRow) {

        try {
            List<RowData> processedData = fileProcessingService.processFile(file, startRow);
            return ResponseEntity.ok(processedData);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error processing file: " + e.getMessage());
        }
    }
}

