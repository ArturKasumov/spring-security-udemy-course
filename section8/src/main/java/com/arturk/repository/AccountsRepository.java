package com.arturk.repository;

import com.arturk.model.entity.AccountsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<AccountsEntity, Long> {

    AccountsEntity findByCustomerId(long customerId);

}
