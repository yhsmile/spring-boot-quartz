package com.example.demo.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.example.demo.entity.JobConfig;

@Component
public class MyScheduler {
	
	Logger logger = LoggerFactory.getLogger(MyScheduler.class);
	
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	 public void scheduleJobs() throws SchedulerException {
//	        Scheduler scheduler = schedulerFactoryBean.getScheduler();
//	        startJob1(scheduler);
	    }


	public void addJobs(List<JobConfig> list) throws SchedulerException {
		if(null == list || list.size() == 0){
			logger.info("没有查询到任务数据");
			return;
		}
		for(JobConfig config : list){
			addJob(config);
		}
	}
	
	public void addJob(JobConfig job) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		if(null != job){
			String name = job.getJobName();
			String desc = job.getJobDesc();
			String group = job.getJobGroup();
			JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class).withIdentity(name, group).withDescription(desc).build();
			String cron = job.getJobCron();
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
			CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}
	}
	
	/**
	 * @description 更新任务的触发器时间
	 * @param name
	 * @param group
	 * @param cronExpression
	 * @throws SchedulerException
	 */
	public void updateCronTrigger(String name,String group,String cronExpression) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		//表达式调度构造器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
		//按新的 cronExpression 表达式重新构建 trigger
		TriggerKey key = new TriggerKey(name, group);
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(key);
		trigger = trigger.getTriggerBuilder().withIdentity(key).withSchedule(scheduleBuilder).build();
		//按新的 trigger 重新设置 job 执行
		scheduler.rescheduleJob(key, trigger);
	}
	
	/**
	 * @description 暂停一个任务
	 * @param name
	 * @param group
	 * @throws SchedulerException
	 */
	public void pauseJob(String name,String group) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(name,group);  
        scheduler.pauseJob(jobKey);  
	}
	
	/**
	 * @description 恢复一个任务
	 * @param name
	 * @param group
	 * @throws SchedulerException
	 */
	public void resumeJob(String name,String group) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(name,group);  
        scheduler.resumeJob(jobKey); 
	}
	
	/**
	 * @description 删除任务
	 * @param name
	 * @param group
	 * @throws SchedulerException
	 */
	public boolean deleteJob(String name,String group) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(name,group);  
        return scheduler.deleteJob(jobKey);
	}
	
	/**
	 * @description 立即执行任务
	 * @param name
	 * @param group
	 * @throws SchedulerException
	 */
	public void runJobNow(String name,String group) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(name,group);  
        scheduler.triggerJob(jobKey);
	}
	
	/**
	 * @description 获取所有的任务列表
	 * @return List
	 * @throws SchedulerException
	 */
	public List<JobConfig> getAllJobs() throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<JobConfig> jobList = new ArrayList<JobConfig>();
		if(null != jobList && jobList.size() > 0){
			for(JobKey jobKey : jobKeys){
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);  
	            for (Trigger trigger : triggers) {  
	            	JobConfig job = new JobConfig();  
	                job.setJobName(jobKey.getName());  
	                job.setJobGroup(jobKey.getGroup());  
	                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());  
	                job.setJobStatus(triggerState.name());  
	                if (trigger instanceof CronTrigger) {  
	                    CronTrigger cronTrigger = (CronTrigger) trigger;  
	                    String cronExpression = cronTrigger.getCronExpression();  
	                    job.setJobCron(cronExpression);
	                }  
	                jobList.add(job);  
	            } 
			}
		}
		return jobList;
	}
	

}
