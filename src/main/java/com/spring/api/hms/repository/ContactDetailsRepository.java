package com.spring.api.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.api.hms.entity.ContactDetailsEntity;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetailsEntity, Integer> {
	

}
