package com.arturk.repository;

import com.arturk.model.entity.ContactEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<ContactEntity, String> {
	
	
}
