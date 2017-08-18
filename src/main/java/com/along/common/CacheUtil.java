package com.along.common;

import java.util.List;

import com.along.entity.Menu;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtil {
	//private static Logger log = Logger.getLogger(CacheUtil.class);
	//
	public static CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);
	//从静态变量中 获取  springContext 实例 来获取 指定的bean
	public static <T> void remove(String cacheName,  String typeAlis, T cahe) {
		
		if(!cacheManager.cacheExists(cacheName)){
			cacheManager.addCache(cacheName);
		}
		Cache cache = cacheManager .getCache(cacheName);
		
		Element element =new Element(typeAlis,cahe) ;
		cache.removeElement(element);
	}
	
	
	public static <T> void put(String cacheName, String typeAlis, T cahe) {
		
		if(!cacheManager.cacheExists(cacheName)){
			cacheManager.addCache(cacheName);
		}
		Cache cache = cacheManager .getCache(cacheName);
		
		Element element =new Element(typeAlis,cahe) ;
		cache.put(element);
	}


	@SuppressWarnings("unchecked")
	public static <T> List<T> getCahes(String cacheName, String typeAlis, Class<T> class1) {
		if(!cacheManager.cacheExists(cacheName)){
			cacheManager.addCache(cacheName);
		}
		Cache cache = cacheManager .getCache(cacheName);
		Element element = cache.get(typeAlis);
		return (List<T>) element.getObjectValue();
	}
	
}
