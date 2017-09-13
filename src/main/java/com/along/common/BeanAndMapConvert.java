package com.along.common;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.log4j.Logger;

public class BeanAndMapConvert{
	private static Logger log =Logger.getLogger(BeanAndMapConvert.class);
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object>BeanToMap(Object bean, Map<String,Object> map){
		
		
		return JSONObject.fromObject(bean);
	}
	
	
	/**
	 * 将 map转为标准的 javabean对象、
	 * 转换日期格式为 标准的 java.util.date 格式
	 * @param bean
	 * @param map
	 * @return
	 */
	public static <T> T  MapToBean(T bean, Map<String,Object> map){
		
		ConvertUtils.register(new Converter()  
	       {  
	           @SuppressWarnings("rawtypes")  
	           public Object convert(Class arg0, Object arg1)  
	           {  
	               System.out.println("注册字符串转换为date类型转换器");  
	               if(arg1 == null)  
	               {  
	                   return null;  
	               }  
	               if(!(arg1 instanceof String))  
	               {  
	                   throw new ConversionException("只支持字符串转换 !");  
	               }  
	                  
	               String str = (String)arg1;  
	               if(str.trim().equals(""))  
	               {  
	                   return null;  
	               }  
	                  
	               SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	                  
	               try{  
	                   return sd.parse(str);  
	               }  
	               catch(ParseException e)  
	               {  
	                   throw new RuntimeException(e);  
	               }  
	                  
	           }  
	              
	       }, java.util.Date.class); 
		try {
			BeanUtils.populate(bean, map);
		} catch (IllegalAccessException e) {
			log.error("类型转换异常"+e);
		} catch (InvocationTargetException e) {
			log.error("java bean  setter getter 异常"+e);
		}
		return bean;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> List<T>  MapToBeanList(List <T> list,Class<T> t, Map<String,Object> map){
		for (Entry<String, Object> mapc : map.entrySet()) {
			Map<String,Object> mapcc = (Map<String, Object>) mapc;
			try {
				list.add(MapToBean(t.newInstance(), mapcc));
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
