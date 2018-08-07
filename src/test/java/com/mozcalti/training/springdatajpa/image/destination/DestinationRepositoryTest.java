package com.mozcalti.training.springdatajpa.image.destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mozcalti.training.springdatajpa.image.destination.model.Destination;
import com.mozcalti.training.springdatajpa.image.destination.repositories.DestinationRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DestinationConfig.class)
public class DestinationRepositoryTest {
	@Autowired
	DestinationRepository repository;
	
	@Test
	public void sampleInsert() {
		Destination origin = new Destination();
		origin.setName("sample");
		origin.setData(new byte[] {1,3,4,5});
		
		repository.save(origin);
	}
}
