package com.spring.api.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.api.hms.entity.UserDetailsEntity;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Integer> {
	
	@Query("select u from UserDetailsEntity u where u.roleDetails.role = 'DOCTOR' and u.specialization = ?1")
	List<UserDetailsEntity> getSpecailization(String specialization);
}
