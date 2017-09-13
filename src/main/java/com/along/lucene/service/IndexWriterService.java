package com.along.lucene.service;

import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;

public interface IndexWriterService {
	
	/**
	 * 创建索引 
	 * @param path 索引位置
	 */
	public abstract void createIndex(String path,Map<String,String> things);
	/**
	 * 查询器 
	 * @param form 字段名  
	 * @query 查询条件
	 * @return  返回  doucument 集合
	 */
	public abstract List<Document> seach(String indexPath,String from,String query);


	public List<Document> Parser(String from,String parse) throws Exception;
}
