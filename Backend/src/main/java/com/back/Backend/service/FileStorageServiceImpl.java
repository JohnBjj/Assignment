package com.back.Backend.service;

import com.back.Backend.exceptions.FileNotFoundException;
import com.back.Backend.exceptions.InvalidFileException;
import com.back.Backend.model.File;
import com.back.Backend.repository.FileRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
@NoArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    FileRepository fileRepository;

    @Autowired
    public FileStorageServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    @Override
    public List<File> getAllFiles(int page, int limit) {

        Pageable pageable = PageRequest.of(page, limit);

        List<File> content = fileRepository.findAll(pageable).getContent();

        if (content.size() < 1) {
            return new ArrayList<>();
        }
        return content;
    }
    @Override
    public File getById(Long id) {

       return fileRepository.findById(id).orElseThrow(()-> {
           throw new FileNotFoundException("File not Found");
        });


    }


    @Override
    public void uploadAndSave(MultipartFile file) throws IOException, ParseException {


        List<File> files = new ArrayList<>();

        String data = new String(file.getBytes(), StandardCharsets.UTF_8);

        List<String> splitedList = Arrays.stream(data.split("\n"))
                .map(String::trim)
                .collect(Collectors.toList());

        String[] splitedStrings = splitedList.toArray(String[]::new);


        Arrays.stream(splitedStrings).filter(line -> line.length() == 38)
                .forEach(line -> {
                    try {
                        String docType = line.substring(0, 1);
                        int companyId = Integer.parseInt(line.substring(1, 10));
                        Date date = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(line.substring(10, 18));
                        int docId = Integer.parseInt(line.substring(18, 27));
                        String sign = line.substring(27, 28);
                        int amount = Integer.parseInt(line.substring(28));
                        File fileEntity = File.builder().docType(docType)
                                .companyId(companyId)
                                .date(date)
                                .docId(docId)
                                .sign(sign)
                                .amount(amount)
                                .build();
                        files.add(fileEntity);


                    } catch (InvalidFileException | ParseException e) {
                        throw new InvalidFileException("Invalid File Exception");
                    }
                });


         files.forEach(fileEntity -> fileRepository.save(fileEntity));


    }


    @Override
    public void delete(Long id) {

        fileRepository.findById(id).ifPresentOrElse(
                fileToDelete -> {
                    fileRepository.delete(fileToDelete);
                },
                () -> {
                    throw new FileNotFoundException("File not found");
                }
        );


    }

    @Override
    public void update(File file, Long id) {

        fileRepository.findById(id).ifPresentOrElse(presentFile -> {

                presentFile.setDocType(file.getDocType());
                presentFile.setCompanyId(file.getCompanyId());
                presentFile.setDate(file.getDate());
                presentFile.setDocId(file.getDocId());
                presentFile.setSign(file.getSign());
                presentFile.setAmount(file.getAmount());

                fileRepository.save(file);

                },
                () -> {
                    throw new FileNotFoundException("File not found");
                });


    }



}
