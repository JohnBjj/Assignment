package com.back.Backend.controller;

import com.back.Backend.model.File;
import com.back.Backend.service.FileStorageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/files")
@ApiOperation(value = "/file", tags = "File Controller")
public class FileController {

    private FileStorageService fileStorageService;

    @Autowired
    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @ApiOperation(value = "Fetch all Files", response = Iterable.class)
    @GetMapping
    public ResponseEntity<List<File>> getAllFile(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit" , defaultValue = "10")int limit){

        return ResponseEntity.ok(fileStorageService.getAllFiles(page, limit));
    }

    @ApiOperation(value = "Get one file by ID", response = Iterable.class)
    @GetMapping("/{id}")
    public ResponseEntity<File> getFileById(@PathVariable("id") Long id){



        return ResponseEntity.ok(fileStorageService.getById(id));
    }


    @ApiOperation(value = "Load and save files into database")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, ParseException {

        fileStorageService.uploadAndSave(file);

        return ResponseEntity.ok().build();

    }

    @ApiOperation(value = "Delete file from database")
    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable Long id){
        fileStorageService.delete(id);
    }

    @ApiOperation(value = "Update file from database")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFile(@RequestBody File file,@PathVariable("id") Long id){
        System.out.println(id);
        fileStorageService.update(file, id);

        return ResponseEntity.ok().build();

    }
}
