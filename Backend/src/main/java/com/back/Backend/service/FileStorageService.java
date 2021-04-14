package com.back.Backend.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.stream.Stream;
@Service
public interface FileStorageService {

    public void init();

    public void save(MultipartFile file);

    public void upLoad(MultipartFile file) throws IOException, ParseException;

    public void deleteAll();

    public Stream<Path> loadAll();

}
