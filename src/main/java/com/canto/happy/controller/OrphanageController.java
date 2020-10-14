package com.canto.happy.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.canto.happy.model.Orphanage;
import com.canto.happy.service.OrphanageService;
import com.canto.happy.service.ValidationService;

@RestController
@RequestMapping("/api/orphanages")
public class OrphanageController {

	@Autowired
	private OrphanageService service;

	@Autowired
	private ValidationService validation;

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Orphanage orphanage,
			@RequestParam("images") MultipartFile[] images, BindingResult result) {
		Map<String, String> errors = validation.validate(result);
		if (errors != null) {
			return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
		}

		Orphanage saved = repository.save(orphanage);
		return new ResponseEntity<Orphanage>(saved, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Orphanage>> findAll() {
		List<Orphanage> orphanages = repository.findAll();
		return new ResponseEntity<>(orphanages, HttpStatus.OK);
	}

	@GetMapping("/{orphanageId}")
	public ResponseEntity<Orphanage> findById(@PathVariable String orphanageId) {
		return repository.findById(orphanageId)
				.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}

	@GetMapping("/search")
	public ResponseEntity<List<Orphanage>> findByName(@RequestParam String name) {
		List<Orphanage> result = repository.findByName(name);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{orphanageId}")
	public ResponseEntity<Void> deleteProject(@PathVariable String orphanageId) {
		repository.deleteById(orphanageId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
