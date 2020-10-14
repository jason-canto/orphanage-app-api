package com.canto.happy.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.canto.happy.model.Orphanage;

public interface OrphanageRepository extends MongoRepository<Orphanage, String> {

	List<Orphanage> findByName(String name);

}
