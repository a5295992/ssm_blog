package com.along.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.along.common.DateUtil;
import com.along.entity.LogExample;
import com.along.serviceImpl.LogServiceImpl;


@Service
@Lazy(false)
public class CleanDeleTask {
	@Autowired
	private LogServiceImpl logService;
	private Logger log = Logger.getLogger(CleanDeleTask.class);
	@Scheduled(cron = "0 0 2 * * ?")
	public void cleanData(){
		LogExample example = new LogExample();
		log.info("计划任务开始:"+DateUtil.toUtilDate());
		//删除 在此日志之前的 日志
		example.createCriteria().andCreateDateBetween(DateUtil.toSqlDate("2017-00-00 22:25:52"), DateUtil.toSqlDate());
		
		logService.deletes(example );
		
		
		log.info("计划任务结束:"+DateUtil.toUtilDate());
	}
}
