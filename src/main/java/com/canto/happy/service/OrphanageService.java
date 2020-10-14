package com.canto.happy.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.canto.happy.model.Orphanage;
import com.canto.happy.repository.OrphanageRepository;

public class OrphanageService {

	@Autowired
	private OrphanageRepository repository;

	public Orphanage save(Orphanage orphanage) {
		return repository.save(orphanage);
	}

}
