package br.com.flyway.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.flyway.domain.Rotina;
import br.com.flyway.repository.RotinaRepository;

@RestController
@RequestMapping("/api/rotinas")
public class RotinaController {
	
	private RotinaRepository repository;

	@Autowired
	public RotinaController(RotinaRepository repository) {		
		this.repository = repository;
	}
	
	@GetMapping()	
	public Iterable<Rotina> index(			
			@SortDefault.SortDefaults({
				@SortDefault(sort = "descricao", direction = Sort.Direction.ASC) }) Pageable pageable) {		
		
		return repository.findAll(pageable);
	}
	
	@GetMapping("{id}")	
	public ResponseEntity<Rotina> load(@PathVariable(value = "id") Long id) {
		return repository.findById(id)
				.map(record -> {										
					return ResponseEntity.ok().body(record);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping()
	public ResponseEntity<Rotina> create(
			@Valid @RequestBody Rotina rotina) {				
		
		repository.save(rotina);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(rotina.getId()).toUri();		

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("{id}")	
	public ResponseEntity<Rotina> update(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Rotina rotina) {		
		
		repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());					
		
		this.repository.save(rotina);		

		return ResponseEntity.ok().build();		
	}
	
	@DeleteMapping("{id}")	
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return repository.findById(id)
		        .map(record -> {        	
		        	repository.deleteById(id);
		            return ResponseEntity.ok().build();
		        }).orElse(ResponseEntity.notFound().build());
	}	
	
	

}
