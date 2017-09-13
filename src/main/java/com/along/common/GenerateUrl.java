package com.along.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import net.sf.json.JSONObject;
import fr.vergne.ioutils.FileUtils;

public class GenerateUrl {
	static String path = "anrequest.json";
	static String generateClassName="AnRequest.java";
	static String generateJSName="AnRequest.js";
	static String head = "package com.along.common;\r\n";
	
	private static String base_string = "\tpublic static final String ";
	private static String base_string2 ="=\"/";
	private static String base_string3 ="\";\r\n";
	static String className = "public class AnRequest {\r\n";
	public static List<String> list =new ArrayList<String>();
	
	static String  jsHead ="//CopyRight -2017\r\n"; 
	
	private static String adminPath= null;
	private static String frontPath= null;
	private static String function_add= null;
	private static String function_update= null;
	private static String function_delete= null;
	private static String function_query= null;
	private static String function_view= null;
	private static String entity_package= null;
	private static Map<String,String> config= null;
	public static void main(String[] args) throws Exception {
		/*init();
		list.add("Menu");
		list.add("User");
		list.add("Role");
		list.add("UrlEntity");
		list.add("Log");
		generateJava();
		generateJS();*/
//		/readClassNameToarray("com.along.entity");
		//
		
		createEntityAndMapper();
	}

	private static void generateJS() throws IOException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(jsHead);
		base_string = "var ";
		addBody(base_string,sb,adminPath);
		sb.append("\r\n");
		sb.append("\r\n");
		addBody(base_string,sb,frontPath);
		System.out.println(sb);
		File newFile = new File(generateJSName);
		if(!newFile.exists()){
			newFile.createNewFile();
		}
		FileUtils.write(newFile, sb.toString());
		
	}

	@SuppressWarnings("unchecked")
	private static void  init() throws Exception{
		File file = new File(path);
		String content = FileUtils.readFileToString(file);
		config = JSONObject.fromObject(content);
		
		adminPath		= config.get("adminPath");
		frontPath		= config.get("frontPath");
		function_add	= config.get("function_add");
		function_update = config.get("function_update");
		function_delete = config.get("function_delete");
		function_query 	= config.get("function_query");
		function_view   = config.get("function_view");
		entity_package  = config.get("entity_package");
	}
	
	private static void generateJava() throws IOException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(head);
		sb.append(className);
		addBody(base_string,sb,adminPath);
		sb.append("\t//*------------------*//r\n");
		addBody(base_string,sb,frontPath);
		sb.append("}");
		
		
		System.out.println(sb);
		File newFile = new File("src/main/java/com/along/common/"+generateClassName);
		if(!newFile.exists()){
			newFile.createNewFile();
		}
		FileUtils.write(newFile, sb.toString());
	}


	private static void addBody(String base_string,StringBuilder sb,String backOrFront) {
		//add
		List<String> list = readClassNameToarray(entity_package);
		for (int i = 0; i < list.size(); i++) {
			
			String c = list.get(i).toLowerCase();
			sb.append("\t//*"+list.get(i)+"---start--------*/\r\n");
			String key = base_string+backOrFront.toUpperCase()+"_"+c.toUpperCase()+"_"
					+function_add.toUpperCase()+base_string2+backOrFront+"/"+c+"/"+function_add+base_string3;
			String value  = base_string+backOrFront.toUpperCase()+"_"+c.toUpperCase()+"_"
					+function_add.toUpperCase()+"_"+function_view.toUpperCase()+base_string2+backOrFront+"/"+c+"/"+function_add+"/"+function_view+base_string3;
			sb.append(key+value);
			key = base_string+backOrFront.toUpperCase()+"_"+c.toUpperCase()+"_"
					+function_update.toUpperCase()+base_string2+backOrFront+"/"+c+"/"+function_update+base_string3;
			value  = base_string+backOrFront.toUpperCase()+"_"+c.toUpperCase()+"_"
					+function_update.toUpperCase()+"_"+function_view.toUpperCase()+base_string2+backOrFront+"/"+c+"/"+function_update+"/"+function_view+base_string3;
			sb.append(key+value);
			key = base_string+backOrFront.toUpperCase()+"_"+list.get(i).toUpperCase()+"_"
					+function_delete.toUpperCase()+base_string2+backOrFront+"/"+c+"/"+function_delete+base_string3;
			value  = base_string+backOrFront.toUpperCase()+"_"+c.toUpperCase()
					+"_"+function_delete.toUpperCase()+"_"+function_view.toUpperCase()+base_string2+backOrFront+"/"+c+"/"+function_delete+"/"+function_view+base_string3;
			sb.append(key+value);
			key = base_string+backOrFront.toUpperCase()+"_"+c.toUpperCase()+"_"
					+function_query.toUpperCase()+base_string2+backOrFront+"/"+c+"/"+function_query+base_string3;
			value  = base_string+backOrFront.toUpperCase()+"_"+c.toUpperCase()+"_"
					+function_query.toUpperCase()+"_"+function_view.toUpperCase()+base_string2+backOrFront+"/"+c+"/"+function_query+"/"+function_view+base_string3;
			sb.append(key+value);
		}
	}

	private static List<String> readClassNameToarray(String entity_package) {
		
		return list;
	}
	
	
	private static String  generator = "generator-config.xml";
	
	
	public static void createEntityAndMapper() throws Exception{
		
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File(generator);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
				callback, warnings);
		myBatisGenerator.generate(null);
	}
	
}
