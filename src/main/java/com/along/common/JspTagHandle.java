package com.along.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class JspTagHandle {
	

	public static String  replace(String text,String rgex,String replacement) {
		String b = StringUtils.replacePattern(text, rgex, replacement);
		
		if(b.length()<150){
			return b;
		}
		return b.substring(0, 150);
	}
	
	public static List<String>  cutString(String text,String before,String after) {
		//String rgex = "<img(.*?)>";  
		
		if(text.contains(before)){
			String[] a =null;
			try {
				a = StringUtils.substringsBetween(text, before, after);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(a!=null && a.length>0){
				
				if(a.length>3){
					return Arrays.asList(a[0],a[1],a[2]);//只取三位
				}
				return Arrays.asList(a);
			}
		}
		
		return new ArrayList<String>();
	}
	
	public static void main(String[] args) {
		String a = ""
				 +"最近我又看了一部青春偶像喜剧电影，"
				 +"<a href=#>  <img src='/ssm_blog/statics/bootstrap/img/pic.jpeg' alt=\"\"> </a>"
				 +"是不是很好笑";
		
		List<String> vb= cutString(a,"src=","alt");
		System.out.println(vb);
	}
}
