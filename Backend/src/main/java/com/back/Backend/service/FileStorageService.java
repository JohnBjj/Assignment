package com.back.Backend.service;

import com.back.Backend.model.File;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;


public interface FileStorageService {

    public List<File> getAllFiles(int page, int limit);

    public void uploadAndSave(MultipartFile file) throws IOException, ParseException;

    public void delete(Long id);

    public void update(File file, Long id);

    public File getById(Long id);


}
