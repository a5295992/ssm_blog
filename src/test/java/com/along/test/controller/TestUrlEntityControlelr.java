package com.along.test.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Observable;
import java.util.Map.Entry;
import java.util.Observer;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.codehaus.jackson.map.util.BeanUtil;
import org.junit.Test;

import com.along.common.DateUtil;
import com.along.entity.UrlEntity;

public class TestUrlEntityControlelr {

	@SuppressWarnings("unchecked")
	@Test
	public void urlEntityAdd() {
		String urlEntitys = "{'id':{'id':'2','name':'地址','title':'地址4444','url':'/a/user/add','methodType':'post','description':'用户管理目录','fileName':'AnRequest.js','fileType':'js','filePath':'www/ww/w'}}";

	}

	public static void main(String[] args) throws Exception {
		UrlEntity urlEntity = new UrlEntity();
		urlEntity.setName("222222222");
		urlEntity.setCreateDate(DateUtil.toUtilDate());
		urlEntity.setUpdateDate(DateUtil.toUtilDate());
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,
				new JsonValueProcessor() {
					private String format = "yyyy-MM-dd HH:mm:ss";

					public Object processArrayValue(Object arg0, JsonConfig arg1) {
						return process(arg0);
					}

					public Object processObjectValue(String arg0, Object arg1,
							JsonConfig arg2) {
						return process(arg1);
					}

					private Object process(Object value) {
						if (value instanceof Date
								|| value instanceof java.util.Date) {
							SimpleDateFormat sdf = new SimpleDateFormat(format,
									Locale.UK);
							return sdf.format(value);
						}
						return value == null ? "" : value.toString();
					}

				});
		@SuppressWarnings("unchecked")
		Map<String, Object> urlEntityss = JSONObject.fromObject(urlEntity,
				config);
		System.out.println(urlEntityss);
		UrlEntity u = new UrlEntity();
		ConvertUtils.register(new Converter() {

			@SuppressWarnings("rawtypes")
			public Object convert(Class arg0, Object arg1) {
				System.out.println("注册字符串转换为date类型转换器");
				if (arg1 == null) {
					return null;
				}
				if (!(arg1 instanceof String)) {
					throw new ConversionException("只支持字符串转换 !");
				}

				String str = (String) arg1;
				if (str.trim().equals("")) {
					return null;
				}

				SimpleDateFormat sd = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				try {
					return sd.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}

			}

		}, java.util.Date.class);
		BeanUtils.populate(u, urlEntityss);
		System.out.println(u.getCreateDate());

	}

}
