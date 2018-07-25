package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.JobConfig;
import com.example.demo.repository.ConfigRepository;

@Controller
@RequestMapping(value = "/job")
public class JobConfigController {
	
	@Autowired
	private ConfigRepository configRepository;
	
	@RequestMapping(value = "/list")
	public String list(Model model){
		Sort sort = new Sort(Sort.Direction.ASC,"jobGroup");
		List<JobConfig> list = configRepository.findAll(sort);
		model.addAttribute("jobList", list);
		return "index";
	}

}
