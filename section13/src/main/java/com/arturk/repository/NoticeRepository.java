package com.arturk.repository;

import com.arturk.model.entity.NoticeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends CrudRepository<NoticeEntity, Long> {
	
	@Query(value = "from NoticeEntity n where CURDATE() BETWEEN noticBegDt AND noticEndDt")
	List<NoticeEntity> findAllActiveNotices();

}
