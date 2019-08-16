package br.com.flyway.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.flyway.ValidatorUtil;
import br.com.flyway.domain.dto.DocumentoUploadDTO;
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
	
	@PostMapping("/model")	
	public ResponseEntity<Object> create(
			@RequestParam("file") MultipartFile file,
			@RequestParam("documento") String documentoUploadDTOParam) throws IOException {	
		
		ObjectMapper mapper = new ObjectMapper(); 
		DocumentoUploadDTO documentoUploadDTO =  new DocumentoUploadDTO();
		Map<Object, Object> response = new HashMap<Object, Object>();
		
		documentoUploadDTO = mapper.readValue(documentoUploadDTOParam, DocumentoUploadDTO.class);
		
		try {
			//Valida
			ValidatorUtil.valid(documentoUploadDTO);			
		} catch (ConstraintViolationException cve) {
			 for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {
		         System.out.println("property name: " + cv.getPropertyPath() + " property: " + cv.getMessage());
		         response.put(cv.getPropertyPath(), cv.getMessage());
		     }
		
		
			return ResponseEntity.badRequest().body(response);			
		}
		
				
		
		
		/*if(file.isEmpty()) {		
			result.addError(new FieldError("DocumentoUploadDTO", "file", "Informe arquivo"));						
		}*/
		
		fileService.storeFile(file);		
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(1L).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping()
    public ResponseEntity<Resource> downloadFile() throws MalformedURLException {
		Resource file = fileService.loadFile();
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "bla.pdf" + "\"")
	        .body(file);
    }

}
