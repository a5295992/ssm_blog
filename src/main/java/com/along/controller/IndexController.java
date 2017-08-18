package com.along.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.along.common.LocationConstant;
import com.along.common.SpringContextHolder;
import com.along.entity.Menu;
import com.along.entity.MenuExample;
import com.along.serviceImpl.MenuServiceImpl;
@Controller
public class IndexController {
	private Logger  log = Logger.getLogger(IndexController.class);
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	@RequestMapping(value="/f/index")
	public ModelAndView f_index(){
		
		ApplicationContext app = SpringContextHolder.getApplicationContext();
		
		log.info("f_index: app = "+app);
		return new ModelAndView(LocationConstant.f_index);
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value="/a/index")//后台主页
	public ModelAndView a_index(){
		MenuExample f = new MenuExample();
		f.createCriteria().andPidEqualTo("0");
		List<Menu> menuList = menuServiceImpl.getList(f ,true);
		return new ModelAndView(LocationConstant.a_index).addObject("menuList", menuList);
	}
}
