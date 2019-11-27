package br.com.flyway.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.flyway.exception.ApiValidationError;
import br.com.flyway.exception.AppException;

@RestController
@RequestMapping("/api")
public class Controller {
	
	
	@GetMapping("errors")	
	public ResponseEntity<?> index() {
		
		List<ApiValidationError> errors = new ArrayList<ApiValidationError>();
		errors.add(new ApiValidationError("", "campo1", "mensagem 1"));
		errors.add(new ApiValidationError("", "campo2", "mensagem 2"));
		errors.add(new ApiValidationError("", "campo3", "mensagem 3"));
		errors.add(new ApiValidationError("", "campo4", "mensagem 4"));
		
		if (!errors.isEmpty()) {			
			throw new AppException(errors);			
		}
		
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("error")	
	public ResponseEntity<?> index2() {		
		
		if (true) {			
			throw new AppException("campo5", "mensagem 5");			
		}
		
		
		return ResponseEntity.ok().build();
	}


}
