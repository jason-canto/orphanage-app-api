package com.canto.happy.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.canto.happy.model.Orphanage;
import com.canto.happy.model.Photo;
import com.canto.happy.repository.OrphanageRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrphanageService {

	@Autowired
	private OrphanageRepository repository;

	public Orphanage save(Orphanage orphanage,  MultipartFile[] images) {
		List<Photo> photos = Arrays.asList(images).stream()
			.map(this::toPhoto)
			.collect(Collectors.toList());
		orphanage.setImages(photos);
		return repository.save(orphanage);
	}

	public List<Orphanage> findAll() {
		return repository.findAll();
	}

	public Optional<Orphanage> findById(String id) {
		return repository.findById(id);
	}

	public List<Orphanage> findByName(String name) {
		return repository.findByName(name);
	}

	public void delete(String id) {
		repository.deleteById(id);
	}

	private Photo toPhoto(MultipartFile file) {
		try {
			return new Photo(UUID.randomUUID().toString(),
					new Binary(BsonBinarySubType.BINARY, file.getBytes()));
		} catch (IOException ex) {
			log.error("Error saving image", ex);
		}
		return null;
	}

}
