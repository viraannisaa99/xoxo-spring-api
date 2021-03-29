package com.example.xoxo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.xoxo.models.FileInfo;


public interface FileRepository extends JpaRepository<FileInfo, Long> {

}
