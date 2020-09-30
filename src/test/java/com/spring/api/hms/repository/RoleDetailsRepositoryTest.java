package com.spring.api.hms.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.api.hms.entity.RoleDetailsEntity;
import com.spring.api.hms.enums.RoleEnum;

import java.util.List;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleDetailsRepositoryTest {

	@Autowired
	RoleDetailsRepository roleDetailsRepository;

	@BeforeEach
	protected void setUp() throws Exception {

	}

	@Test
	public void testFindByEmail() throws Exception {
		RoleDetailsEntity roleDtlsEntity = roleDetailsRepository.findByEmail("chandra@gmail.com");
		Assert.assertNotNull(roleDtlsEntity.getEmail());
		Assert.assertNotNull(roleDtlsEntity.getRole());

	}

	@Test
	public void testFindAllByRole() throws Exception {
		List<RoleDetailsEntity> roleDtlsEntityList = roleDetailsRepository.findAllByRole(RoleEnum.ADMIN);
		if (roleDtlsEntityList.size() == 0) {
			roleDtlsEntityList = null;
		}
		Assert.assertNotNull(roleDtlsEntityList);
	}

}
