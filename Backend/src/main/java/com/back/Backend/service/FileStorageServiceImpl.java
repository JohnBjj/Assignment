package com.back.Backend.service;

import com.back.Backend.model.File;
import com.back.Backend.repository.FileRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Date;
import java.util.stream.Stream;


public class FileStorageServiceImpl implements FileStorageService {

    FileRepository fileRepository;

    @Autowired
    public FileStorageServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileStorageServiceImpl() throws FileNotFoundException {
    }

    @Override
    public void init() {

    }

    @Override
    public void save(MultipartFile file) {

    }

    @Override
    public void upLoad(MultipartFile file) throws IOException, ParseException {

        String data = new String(file.getBytes(), StandardCharsets.UTF_8);
        String docType = data.substring(0, 1);
        Integer companyId = Integer.parseInt(data.substring(1, 10));
        Date date = DateUtils.parseDate(data.substring(10, 19), "yyyy/MM/dd");
        Integer docId = Integer.parseInt(data.substring(19, 28));
        String sign = data.substring(28, 29);
        Integer amount = Integer.parseInt(data.substring(29, 39));

        File fileEntity = File.builder().docType(docType)
                .companyId(companyId)
                .date(date)
                .dockId(docId)
                .sign(sign)
                .amount(amount)
                .build();

        fileRepository.save(fileEntity);




    }


    @Override
    public void deleteAll() {

    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }
}
