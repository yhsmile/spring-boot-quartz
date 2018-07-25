package com.example.demo.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import com.example.demo.enums.UrgeEnum;
import com.example.demo.util.DateUtil;

public class ScheduledJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		JobDetail detail = context.getJobDetail();
//		System.out.println("描述：" + detail.getDescription());
		JobKey jobKey = detail.getKey();
		String name = jobKey.getName();
		String group = jobKey.getGroup();
		String time = DateUtil.date2String(new Date());
		if(UrgeEnum.VoiceType.B.type.equals(group)){
//			System.out.println("key : " + name);
			System.out.println(time + " - B公司执行任务");
		}else if(UrgeEnum.VoiceType.D.type.equals(group)){
//			System.out.println("key : " + name);
			System.out.println(time + " - D公司执行任务");
		}
		
//		System.out.println("hello world  !!! quartz ");

		
		
	}

}
