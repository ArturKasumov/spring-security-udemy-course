package com.arturk.repository;

import com.arturk.model.entity.LoansEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<LoansEntity, Long> {
	
	List<LoansEntity> findByCustomerIdOrderByStartDtDesc(long customerId);

}
