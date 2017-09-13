package com.along.common;

import java.io.Serializable;
import java.util.List;

public class ResultBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer successCount=0;
	private Integer failCount=0;
	
	public Integer getAddCount() {
		return addCount;
	}
	public void setAddCount(Integer addCount) {
		this.addCount = addCount;
	}
	public Integer getDeleCount() {
		return deleCount;
	}
	public void setDeleCount(Integer deleCount) {
		this.deleCount = deleCount;
	}
	public Integer getUpdateCount() {
		return updateCount;
	}
	public void setUpdateCount(Integer updateCount) {
		this.updateCount = updateCount;
	}
	public Integer getQueryCount() {
		return queryCount;
	}
	public void setQueryCount(Integer queryCount) {
		this.queryCount = queryCount;
	}
	private Integer addCount=0;
	private Integer deleCount=0;
	private Integer updateCount=0;
	private Integer queryCount=0;
	
	private String successMessage;
	private List<String> failMessage;
	
	
	
	public ResultBase() {
		super();
	}
	public ResultBase(Integer successCount, Integer failCount,
			String successMessage, List<String> failMessage) {
		super();
		this.successCount = successCount;
		this.failCount = failCount;
		this.successMessage = successMessage;
		this.failMessage = failMessage;
	}
	public Integer getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}
	public Integer getFailCount() {
		return failCount;
	}
	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public List<String>  getFailMessage() {
		return failMessage;
	}
	public void setFailMessage(List<String> failMessage ) {
		this.failMessage = failMessage;
	}
	
}
