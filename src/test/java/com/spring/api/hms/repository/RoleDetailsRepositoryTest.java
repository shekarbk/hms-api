package com.spring.api.hms.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.api.hms.entity.RoleDetailsEntity;
import com.spring.api.hms.enums.RoleEnum;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
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
