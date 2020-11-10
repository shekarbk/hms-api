package com.spring.api.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.api.hms.entity.RoleDetailsEntity;
import com.spring.api.hms.enums.RoleEnum;

@Repository
public interface RoleDetailsRepository extends JpaRepository<RoleDetailsEntity, Integer> {
	RoleDetailsEntity findByEmail(String email);

	List<RoleDetailsEntity> findAllByRole(RoleEnum role);

	@Query("select r from RoleDetailsEntity r where r.email like %?1 and r.password like %?2 and r.role = ?3")
	RoleDetailsEntity findByUserDetails(String email, String password, RoleEnum role);

}
