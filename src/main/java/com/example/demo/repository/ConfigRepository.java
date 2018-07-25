package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.JobConfig;

public interface ConfigRepository extends JpaRepository<JobConfig, Long> {
	
	@Query("from JobConfig c where c.id=?1")
	JobConfig findOne(Long id);
	
	List<JobConfig> findAll();
	
	List<JobConfig> findByJobType(String jobType);

}
