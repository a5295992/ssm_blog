package com.along.lucene.serviceImpl;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;

import com.along.lucene.service.IndexWriterService;

@Component
public class IndexService implements IndexWriterService {
	private Logger log =Logger.getLogger(IndexService.class);
	//写入器
	private IndexWriter indexWriter;
	private IndexReader indexReader;
	private String indexPath_default = "/index_temp";
	
	private Directory dir;
	private Analyzer analyzer = new SmartChineseAnalyzer();
	private IndexWriterConfig conf;
	
	private IndexSearcher indexSearcher;
	
	
	
	public void createIndex(String path,Map<String,String> things) {
		if(path != null){
			indexPath_default 	= 	path;
		}
		
		try {
			dir   		= 	FSDirectory.open(Paths.get(indexPath_default));
			conf 		= 	new IndexWriterConfig(analyzer);
			indexWriter = 	new IndexWriter(dir,conf);
		} catch (IOException e) {
			log.error(e);
		}
		
		Document doc = new Document();
		for (Entry<String, String> thing : things.entrySet()) {
			String value 	= 	thing.getValue();
			String name  	=   thing.getKey();
			
			IndexableField field = new TextField(name, value, Field.Store.YES);
			doc.add(field);
		}
		
		try {
			indexWriter.addDocument(doc);
		} catch (IOException e) {
			log.error(e);
		}finally{
			close(indexWriter,indexReader,dir);
		}
		
	}
	
	public List<Document> seach(String indexPath,String from, String query) {
		if(indexPath != null){
			indexPath_default 	= 	indexPath;
		}
		try {
			dir   		= 	FSDirectory.open(Paths.get(indexPath_default));
			indexReader 	= 	DirectoryReader.open(dir);
		} catch (IOException e) {
			log.error(e);
		}
		
		indexSearcher   = 	new IndexSearcher(indexReader); //查询器 
		
		try {
			return Parser(from,query);
		} catch (Exception e) {
			log.error(e);
		}finally{
			close(indexWriter,indexReader,dir);
		}
		
		return null;
	}
	
	public List<Document> Parser(String from,String parse) throws Exception{
		//Analyzer analyzer = new StandardAnalyzer();//创建分词器
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
	
	
	/**
	 * 关闭  查询索引资源
	 * @param indexWriter
	 * @param indexReader
	 */
	public void close(IndexWriter indexWriter,IndexReader indexReader,Directory dir){
		
		if(indexWriter != null){
			
			try{
				indexWriter.close();
			}catch(Exception e){
				log.error(e);
			}
		}
		
		if(indexReader != null){
			try{
				indexReader.close();
			}catch(Exception e){
				log.error(e);
			}
		}
		
		if(dir != null){
			try{
				dir.close();
			}catch(Exception e){
				log.error(e);
			}
		}
	}
}
