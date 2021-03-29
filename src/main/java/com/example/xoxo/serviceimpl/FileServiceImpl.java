package com.example.xoxo.serviceimpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.xoxo.controllers.ImageController;
import com.example.xoxo.models.FileInfo;
import com.example.xoxo.repository.FileRepository;
import com.example.xoxo.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	FileRepository fileRepository;

	private final Path root = Paths.get("uploads");

	@Override
	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	@Override
	public void save(@RequestParam("files") MultipartFile[] files, Model model) {
		try {
			List<FileInfo> storedFile = new ArrayList<FileInfo>();
			for (MultipartFile file : files) {
				String url = MvcUriComponentsBuilder
						.fromMethodName(ImageController.class, "getFile", file.getOriginalFilename().toString()).build().toString();

				FileInfo fileModel = new FileInfo(file.getOriginalFilename(), url, file.getBytes());
				fileModel.setFile(file.getBytes());
				storedFile.add(fileModel);
				
				Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
			}
			// Save all Files to database
			fileRepository.saveAll(storedFile);
		}catch(Exception e) {
			throw new RuntimeException("Could not save the files!");
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}
	
	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
//			return imageRepository.findAll().stream();
		} catch (Exception e) {
			throw new RuntimeException("Could not load the files!");
		}
	}
}
