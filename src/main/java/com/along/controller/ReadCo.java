package com.along.controller;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.web.bind.annotation.RequestMapping;

import com.along.annotation.ControllerLog;
import com.along.annotation.TestParams;
import com.along.entity.UrlEntity;

public class ReadCo  {

	public static List<UrlEntity> getUrlForm(String basePackage) throws ClassNotFoundException {
		//"com.along.controller"
		ClassPathScanningCandidateComponentProvider c = new ClassPathScanningCandidateComponentProvider(true);
		
		Set<BeanDefinition> sd = c.findCandidateComponents(basePackage);
		List<UrlEntity> urls = new ArrayList<UrlEntity>();
		for (BeanDefinition beanDefinition : sd) {
			
			String className = beanDefinition.getBeanClassName();
			Class<?> clazz = Class.forName(className);
			List<UrlEntity> urlss = getUrlEntityFromClazz(clazz);
			urls.addAll(urlss);
		}
		return urls;
	}

	private static List<UrlEntity> getUrlEntityFromClazz(Class<?> clazz) {
		List<UrlEntity> urls = new ArrayList<UrlEntity>();
		Method []methods = clazz.getMethods();
		for (Method method : methods) {
			UrlEntity url = new UrlEntity();
			RequestMapping re = method.getAnnotation(RequestMapping.class);
			if(re!=null){
				String []value = re.value();
				if(value != null&& value.length>0){
					url.setUrl(value[0]);
				}
				
				ControllerLog desc = method.getAnnotation(ControllerLog.class);
				if(desc!=null){
					url.setDescription(desc.description());
				}else{
					break;
				}
				
				TestParams testParams = method.getAnnotation(TestParams.class);
				if(testParams!=null){
					url.setTestParams(testParams.params());
					url.setMethodType(testParams.methodType());
				}
				
				urls.add(url);
			}else{
				break;
			}
		}
		
		return urls;
	}
	
}
