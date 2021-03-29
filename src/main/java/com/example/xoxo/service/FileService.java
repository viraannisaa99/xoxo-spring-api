package com.example.xoxo.service;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.xoxo.models.FileInfo;

public interface FileService {
	public void init();
	public void save(MultipartFile[] file, Model model);
	public Resource load(String filename);
	public void deleteAll();
	public Stream<Path> loadAll();
}
