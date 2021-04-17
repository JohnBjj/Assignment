package com.back.Backend.repository;

import com.back.Backend.model.File;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileRepository extends JpaRepository<File, Long> {
}
