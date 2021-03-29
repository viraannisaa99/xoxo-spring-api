package com.example.xoxo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.xoxo.message.ResponseMessage;
import com.example.xoxo.models.FileInfo;
import com.example.xoxo.service.FileService;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/api")

public class ImageController {
	@Autowired
	FileService fileService;

	@PostMapping("/upload")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files, Model model) {
		String message = "";
		try {
			List<String> fileNames = new ArrayList<>();
			Arrays.asList(files).stream().forEach(file -> {
				fileService.save(files, model);
				fileNames.add(file.getOriginalFilename());
			});
			message = "Uploaded the files successfully: " + fileNames;
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}catch(Exception e) {
			message = "Fail to upload files!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}
	@GetMapping("/files")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<FileInfo>> getListFiles() {
		List<FileInfo> fileInfos = fileService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(ImageController.class, "getFile", path.getFileName().toString()).build().toString();

			return new FileInfo(filename, url);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("/files/{filename:.+}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = fileService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
}
