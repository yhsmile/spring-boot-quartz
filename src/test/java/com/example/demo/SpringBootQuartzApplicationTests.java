package com.example.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.JobConfig;
import com.example.demo.repository.ConfigRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootQuartzApplicationTests {
	
	@Autowired
	private ConfigRepository configRepository;
	
	
	@Test
	public void findAll(){
		List<JobConfig> list = configRepository.findAll();
		if(null == list || list.size() == 0){
			return ;
		}
		for(JobConfig job : list){
			System.out.println(job.toString());
		}
	}
	
	@Test 
	public void findOne(){
		JobConfig job = configRepository.findOne(1L);
		System.out.println(job.toString());
	}

	@Test
	public void contextLoads() {
	}

}
