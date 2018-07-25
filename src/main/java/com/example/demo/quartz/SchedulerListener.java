package com.example.demo.quartz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.example.demo.entity.JobConfig;
import com.example.demo.repository.ConfigRepository;

@Configuration
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private MyScheduler myScheduler;
	
	@Autowired
	private ConfigRepository configRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			List<JobConfig> list = configRepository.findAll();
//			myScheduler.addJobs(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(){
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		return schedulerFactoryBean;
	}

}
