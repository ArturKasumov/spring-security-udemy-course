package com.arturk.repository;

import com.arturk.model.entity.CardsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends CrudRepository<CardsEntity, Long> {
	
	List<CardsEntity> findByCustomerId(long customerId);

}
