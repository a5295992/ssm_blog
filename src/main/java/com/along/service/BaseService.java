package com.along.service;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.along.common.DateUtil;
import com.along.common.UserUtils;
import com.along.entity.DataEntity;
import com.along.security.ShiroUser;


public class BaseService<T extends DataEntity<T>>  {
	
	private Logger log = Logger.getLogger(BaseService.class);
	public void setInsertValue(T dataEntity){
		
		//只要是新增 我们主键的生成策略都是在这个地方
		dataEntity.setId(UUID.randomUUID().toString().replace("-", ""));
		
		dataEntity.setDelFlag("0");
		ShiroUser user = null;
		try {
			user = UserUtils.getUser();
		} catch (Exception e) {
			log.error(e);
		}
		
		
		if(dataEntity.getCreateBy()==null){
			String createBy = user!=null? user.getLoginName():"匿名用户";
			dataEntity.setCreateBy(createBy );
		}
		
		if(dataEntity.getCreateDate()==null){
			dataEntity.setCreateDate(DateUtil.toSqlDate());
		}
		
		if(dataEntity.getUpdateBy()==null){
			String updateBy = user!=null? user.getLoginName():"匿名用户";
			dataEntity.setUpdateBy(updateBy);
		}
		if(dataEntity.getUpdateDate()==null){
			dataEntity.setUpdateDate(DateUtil.toSqlDate());
		}
		
	}
	
	public void setUpdateValue(T dataEntity){
		
		ShiroUser user = null;
		try {
			user = UserUtils.getUser();
		} catch (Exception e) {
			log.error(e);
		}
		if(dataEntity.getUpdateBy()==null){
			String updateBy = user!=null? user.getLoginName():"匿名用户";
			dataEntity.setUpdateBy(updateBy );
		}
		if(dataEntity.getUpdateDate()==null){
			dataEntity.setUpdateDate(DateUtil.toSqlDate());
		}
	}
}
