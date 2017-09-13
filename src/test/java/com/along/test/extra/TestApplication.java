package com.along.test.extra;

import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.along.lucene.service.IndexQuery;
import com.along.lucene.service.IndexWriterService;

public class TestApplication {

	static String path = "/spring-context.xml";
	static ApplicationContext app;

	@Before
	public void setup() {
		app = new ClassPathXmlApplicationContext(path);
	}

	@Test
	public void testGetapp() {
		IndexWriterService index = app.getBean(IndexWriterService.class);
		System.out.println(index);
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "小三你好的歌终于等到了今天");
		map.put("age", "22的歌终于等到了今天");
		map.put("sex", "难忘的歌终于等到了今天，等了好久终于把梦实现，一声长路漫漫无心睡眠");
		index.createIndex("index_", map);
	}

	@Test
	public void testSearcher() {
		IndexWriterService index = app.getBean(IndexWriterService.class);
		System.out.println(index);
		index.seach("index_", "name", "了");

		/*
		 * for (Document doc : ) { System.out.println(doc.get("name")); }
		 */
	}

	public static void main(String[] args) throws Exception {
		IndexQuery index = new IndexQuery("index_02");

		index.Parser("long1", "names");
	}
	/*
	 * public static void main(String[] args) { app = new
	 * ClassPathXmlApplicationContext(path); IndexWriterService index =
	 * app.getBean( IndexWriterService.class); System.out.println(index);
	 * Map<String,String> map = new HashMap<String, String>(); map.put("name",
	 * "小二"); map.put("age", "19"); map.put("sex", "男"); index.createIndex(null,
	 * map); }
	 */
}
