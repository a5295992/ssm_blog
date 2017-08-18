package com.along.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.along.entity.MenuExample.Criteria;

import fr.vergne.ioutils.FileUtils;

public class Controllergenerate {
	static String path = "anrequest.json";
	static Logger log = Logger.getLogger(Controllergenerate.class);
	public static String fileName="src/main/java/com/along/controller/";
	
	private static String head = "package com.along.controller;\r\n";
	private static String import_ = "import ";
	private static String import_name = "java.util.Date";
	private static String className_start = "public class  ";
	private static String className = "";
	private static String className_end = " {\r\n";
	private static String className_last = " }";
	
	private static String method_start = "\tpublic ";
	private static String method_name = "a_user_add";
	private static Map<String,String> config;
	
	private static Map<String,List<String>> map = new HashMap<String, List<String>>();
	
	public static void main(String[] args) throws Exception {
		init();
		generate();
		
	}

	@SuppressWarnings("unchecked")
	private static void init() throws IOException {
		File file = new File(path);
		String content = FileUtils.readFileToString(file);
		config = JSONObject.fromObject(content);
		List<String> list = readClassNameToarray(config.get("entity_package"));
		addSb(list);
	}

	private static void addSb(List<String> list) {
		for (String className : list) {
			Controllergenerate.className = className;
			List<String> methodList = new ArrayList<String>();
			getmethodList(methodList,config.get("adminPath"));
			getmethodList(methodList,config.get("frontPath"));
			map.put(className, methodList);
		}
	}

	private static void getmethodList(List<String> methodList,String path) {
		String add   =  path+"_"+className+"_"+config.get("function_add");
		String update = path+"_"+className+"_"+config.get("function_update");
		String delete = path+"_"+className+"_"+config.get("function_delete");
		String query  = path+"_"+className+"_"+config.get("function_query");
		String add_view = add+"_"+config.get("function_view");
		String update_view =update+"_"+config.get("function_view");
		String delete_view = delete+"_"+config.get("function_view");
		String query_view =query+"_"+config.get("function_view");
		methodList.addAll(Arrays.asList(add,update,delete,query,add_view,update_view,delete_view,query_view));
	}

	private static void generate() {
		for (Entry<String, List<String>> unitEntity : map.entrySet()) {
			className = unitEntity.getKey();
			StringBuilder sb = new StringBuilder();
			import_name=import_+"org.springframework.stereotype.Controller;\r\n";
			import_name = import_name+import_+"org.springframework.web.bind.annotation.RequestMapping;\r\n";
			import_name = import_name+import_+"com.along.common.AnRequest;\r\n";
			import_name = import_name+import_+"org.springframework.web.bind.annotation.ResponseBody;\r\n";
			import_name = import_name+import_+"org.springframework.web.servlet.ModelAndView;\r\n";
			import_name = import_name+import_+"javax.servlet.http.HttpServletRequest;\r\n";
			import_name = import_name+import_+"javax.servlet.http.HttpServletResponse;\r\n";
			sb.append(head);
			sb.append(import_name);
			sb.append("@Controller\r\n");
			sb.append(className_start+className+"Controller"+className_end);
			List<String> method  = unitEntity.getValue();
			for (String methodName : method) {
				addMethod(methodName,sb);
			}
			sb.append("\r\n"+className_last);
			fileName = unitEntity.getKey();
			save(className+"Controller.java",sb);
		}
		
	}
	
	private static void  addMethod(String method_name,StringBuilder sb){
		if(method_name.contains("view")){
			sb.append("\t@RequestMapping(value=AnRequest."+method_name.toUpperCase()+")\r\n");
			sb.append("\t"+method_start);
			sb.append(" ModelAndView "+method_name.toLowerCase() +"(HttpServletRequest request,HttpServletResponse response){\r\n");
		}else{
			sb.append("\t@ResponseBody\r\n");
			sb.append("\t@RequestMapping(value=AnRequest."+method_name.toUpperCase()+", produces = \"text/html;charset=UTF-8;\")\r\n");
			sb.append("\t"+method_start);
			sb.append(" String "+method_name.toLowerCase() +"(HttpServletRequest request,HttpServletResponse response){\r\n");
		}
		sb.append("\t\treturn null;\r\n");
		sb.append("\t\t}");
		sb.append("\r\n");
		sb.append("\r\n");
	}
	private static void save(String fileName2, StringBuilder sb) {
		
		File file = new File("src/main/java/com/along/controller/"+fileName2);
		System.out.println(file);
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			FileUtils.write(file, sb.toString());
			System.out.println(sb);
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	private static List<String> readClassNameToarray(String entity_package) {
		/*File file = new File("src/main/java/"+entity_package.replace(".", "/"));
		List<String> list = new ArrayList<String>();
		for (File f : file.listFiles()) {
			if(!f.getName().endsWith("Example.java")){
				list.add(f.getName().replace(".java", ""));
			}
		}*/
		return Arrays.asList("Menu");
	}
}
