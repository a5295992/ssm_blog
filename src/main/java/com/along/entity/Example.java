package com.along.entity;

public class Example<T> {
	
	protected String groupByClause;

	//查询字段 按  ,隔开
	protected String queryClause;
	
	
	protected String limit;

	private String orderByClause;
	
	private int pageCount;
	
	private int pageNum;

	
	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getQueryClause() {
		return queryClause;
	}

	public void setQueryClause(String queryClause) {
		this.queryClause = queryClause;
	}
	public String getGroupByClause() {
		return groupByClause;
	}

	public void setGroupByClause(String groupByClause) {
		this.groupByClause = groupByClause;
	}
	
}
