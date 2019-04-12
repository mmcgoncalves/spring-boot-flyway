package br.com.flyway.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.flyway.service.FileService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/documentos")
public class DocumentoController {

	private final FileService fileService;

	@Autowired
	public DocumentoController(FileService fileService) {
		this.fileService = fileService;
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.OK)
	public void handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		fileService.storeFile(file);		
	}
	
	@GetMapping()
    public ResponseEntity<Resource> downloadFile() throws MalformedURLException {
		Resource file = fileService.loadFile();
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "bla.pdf" + "\"")
	        .body(file);
    }

}
