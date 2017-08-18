package com.along.service;

import java.util.UUID;

import com.along.common.DateUtil;
import com.along.entity.DataEntity;


public class BaseService<T extends DataEntity<T>>  {
	
	
	public void setInsertValue(T dataEntity){
		dataEntity.setId(UUID.randomUUID().toString().replace("_", ""));
		dataEntity.setDelFlag("0");
		dataEntity.setCreateDate(DateUtil.toSqlDate());
		dataEntity.setUpdateDate(DateUtil.toSqlDate());
	}
	
	public void setUpdateValue(T dataEntity){
		
		dataEntity.setUpdateDate(DateUtil.toSqlDate());
	}
}
