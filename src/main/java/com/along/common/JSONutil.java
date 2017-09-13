package com.along.common;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JSONutil {
	public static <T> JSONObject formatJsonBeanDate(Page<T> t){
		JsonConfig config = new JsonConfig();
		//提供对实体中sql.Date的格式处理
		config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
		JSONObject jsonO = JSONObject.fromObject(t,config);
		return jsonO;
	}
}
