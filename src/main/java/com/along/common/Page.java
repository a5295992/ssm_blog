package com.along.common;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义 分页实现
 * 
 * @author Along
 * @Date 2017-7-30
 * @param <T>
 */
public class Page<T> implements Serializable {
	/**
	 * pageCount
	 */
	private static final long serialVersionUID = 1L;

	public static final String ROWS = "rows";

	public Page() {

	}

	/**
	 * @param pageCount
	 *            一页显示条数
	 * @param count
	 *            总记录数
	 * @param pageNum
	 *            当前页码数
	 */
	public Page(int pageCount, int total, int pageNum) {
		this.pageCount = pageCount;
		this.total=total;
		this.pageNum = pageNum;
		if (total % pageCount == 0) {
			page = total / pageCount;
		} else {
			page = total / pageCount + 1;
		}
		this.start = pageNum * pageCount;
	}
    private Integer total;
    
	private int start;

	private int end;
	// 多少页
	private int page;
	// 当前页
	private int pageNum;

	// 一页显示多少
	private int pageCount;
	// 数据集合
	private List<T> rows;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
