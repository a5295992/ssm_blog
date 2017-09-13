package com.along.lucene.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class IndexQuery {
	private IndexReader indexReader;
	private IndexSearcher indexSearcher;
	public IndexQuery(){}
	//queryPath 需要查找的目录
	public IndexQuery(String queryPath) throws Exception{
		indexReader = DirectoryReader.open(FSDirectory.open(Paths.get(queryPath)));
		
		indexSearcher = new IndexSearcher(indexReader); //查询器
	}
	
	public List<Document> Parser(String parse,String from) throws Exception{
		Analyzer analyzer = new StandardAnalyzer();//创建分词器
		QueryParser parser = new QueryParser(from, analyzer);//查询器|解析器
		Query query = parser.parse(parse);
		List<Document> docs =getDocument(query);
		return docs;
	}
	
	private List<Document> getDocument(Query query) throws IOException {
		TopDocs  hits = indexSearcher.search(query, 10);
		System.out.println("一共匹配到  : "+hits.totalHits +" 条记录");
		List<Document> list =new ArrayList<Document>();
		for (ScoreDoc sc : hits.scoreDocs) {
			list.add(indexSearcher.doc(sc.doc));
		}
		return list;
	}
	public void close() throws IOException{
		if(indexReader!=null){
			indexReader.close();
		}
	}
}
