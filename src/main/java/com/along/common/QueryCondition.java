package com.along.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.util.WebUtils;

public class QueryCondition {


	public static int pageCount(HttpServletRequest request2, String paramName,
			int defalut) {
		String pageCount = WebUtils.getCleanParam(request2, paramName);
		return pageCount==null?defalut:Integer.parseInt(pageCount);
	}
	
	public static int pageNum(HttpServletRequest request2, String paramName,
			int defalut) {
		String pageNum = WebUtils.getCleanParam(request2, paramName);
		return pageNum==null?0:Integer.parseInt(pageNum)-1;
	}

	public static String pageSort(HttpServletRequest request2, String paramName,
			String default_) {
		String pageSort = WebUtils.getCleanParam(request2, paramName);
		return pageSort==null?"-"+default_:"-"+pageSort;
	}
	
	public static String pageOrder(HttpServletRequest request2, String paramName,
			String default_) {
		String pageOrder = WebUtils.getCleanParam(request2, paramName);
		return pageOrder==null?default_:pageOrder;
	}
	
	public static String pageGroup(HttpServletRequest request2, String paramName,
			String default_) {
		String pageGroup = WebUtils.getCleanParam(request2, paramName);
		return pageGroup==null?default_:pageGroup;
	}
	
}
