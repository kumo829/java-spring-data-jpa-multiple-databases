package com.mozcalti.training.springdatajpa.image.origin;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mozcalti.training.springdatajpa.image.origin.model.Origin;
import com.mozcalti.training.springdatajpa.image.origin.repositories.OriginRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = OriginConfig.class)
public class OriginRepositoryTest {
	@Autowired
	OriginRepository repository;
	
	@Test
	public void sampleInsert() {
		Origin origin = new Origin();
		origin.setName("sample");
		origin.setData(new byte[] {1,3,4,5});
		
		repository.save(origin);
	}
}
