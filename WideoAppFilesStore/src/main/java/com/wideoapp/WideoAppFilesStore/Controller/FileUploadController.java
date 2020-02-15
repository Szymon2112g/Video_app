package com.wideoapp.WideoAppFilesStore.Controller;

import com.wideoapp.WideoAppFilesStore.Storage.StorageFileNotFoundException;
import com.wideoapp.WideoAppFilesStore.Storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.thymeleaf.spring5.expression.Mvc;

import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@CrossOrigin( origins = {"http://localhost:4200", "http://localhost:8100"})
public class FileUploadController {

    private final StorageService storageService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList())
        );
        return "uploadForm";
    }

    @GetMapping("files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping(path = "/addvideofile", consumes = {"multipart/form-data"})
    public ResponseEntity<String> handleFileUpload(@RequestBody MultipartFile file) {
        /*
        MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString()
         */

        String path = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,"serveFile",
                file.getOriginalFilename().toString()).build().toString();

        logger.warn(MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,"serveFile",
                   file.getOriginalFilename().toString()).build().toString());

        storageService.store(file);
        return ResponseEntity.ok(path);
    }

    @GetMapping("/usertest")
    public String gettest() {
        return "testowy user do sprawdzenia";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
