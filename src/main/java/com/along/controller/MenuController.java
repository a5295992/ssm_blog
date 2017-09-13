package com.along.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.along.annotation.ControllerLog;
import com.along.common.AjaxResult;
import com.along.common.AnRequest;
import com.along.common.LocationConstant;
import com.along.entity.Menu;
import com.along.entity.MenuExample;
import com.along.entity.MenuExample.Criteria;
import com.along.serviceImpl.MenuServiceImpl;

@Controller
public class MenuController extends BaseController {
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	private static Logger log = Logger.getLogger(MenuController.class);
	
	/**
	 * 增删改 统一数据接口
	 * @param request
	 * @param response
	 * @return
	 */
	@ControllerLog(description="后台菜单增删改操作")
	@ResponseBody
	@RequestMapping(value = AnRequest.A_MENU_ADD, produces = "text/html;charset=UTF-8;")
	public String a_menu_add(HttpServletRequest request,
			HttpServletResponse response) {
		String menus = WebUtils.getCleanParam(request, "menus");
		log.info("menus : " + menus);
		AjaxResult ajaxResult = new AjaxResult();// 操作数据返回结果集
		if (menus != null) {
			List<Menu> list = getBeanListFormJsonString(menus, Menu.class);// 将字符串集转为对象集

			if (beanValidatorss(list, ajaxResult)) {// 数据验证
				ajaxResult.setSuccessCount(menuServiceImpl.update(list));
			}
		}
		return JSONObject.fromObject(ajaxResult).toString();
	}

	@ResponseBody
	@RequestMapping(value = AnRequest.A_MENU_UPDATE, produces = "text/html;charset=UTF-8;")
	public String a_menu_update(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@ResponseBody
	@RequestMapping(value = AnRequest.A_MENU_DELETE, produces = "text/html;charset=UTF-8;")
	public String a_menu_delete(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}
	
	@ControllerLog(description="后台获取菜单列表")
	@ResponseBody
	@RequestMapping(value = AnRequest.A_MENU_QUERY, produces = "text/html;charset=UTF-8;")
	public String a_menu_query(HttpServletRequest request,
			HttpServletResponse response) {
		//参数获取 
		String  data             = WebUtils.getCleanParam(request, "data");
		@SuppressWarnings("unchecked")
		Map<String,Object> param = (data==null)?new HashMap<String,Object>():JSONObject.fromObject(data);
		String  deleFlag         = WebUtils.getCleanParam(request, "delFlag");
		
		String name              = (String) param.get("name");
		MenuExample f = new MenuExample();
		getExample(request, f);
		Criteria criteria = f.createCriteria();
		if(deleFlag !=null&&(deleFlag.equalsIgnoreCase("1")||deleFlag.equalsIgnoreCase("0"))){
			criteria.andDelFlagIsNotNull();//删除标记为0
		}else{
			criteria.andDelFlagEqualTo("0");//删除标记为0
		}
		//模糊查询
		criteria=name!=null?criteria.andNameLike("%"+name+"%"):criteria;
		
		JSONObject page = menuServiceImpl.getPageToJSONFormatSqlDate(
				f.getPageCount(), f.getPageNum(), f);
		return page == null ? new JSONObject().toString() : page.toString();
		
				//封装参数
	}

	@RequestMapping(value = AnRequest.A_MENU_ADD_VIEW)
	public ModelAndView a_menu_add_view(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@RequestMapping(value = AnRequest.A_MENU_UPDATE_VIEW)
	public ModelAndView a_menu_update_view(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@RequestMapping(value = AnRequest.A_MENU_DELETE_VIEW)
	public ModelAndView a_menu_delete_view(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@RequestMapping(value = AnRequest.A_MENU_QUERY_VIEW)
	public ModelAndView a_menu_query_view(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView(LocationConstant.a_menu_query_view);
	}

	@ResponseBody
	@RequestMapping(value = AnRequest.F_MENU_ADD, produces = "text/html;charset=UTF-8;")
	public String f_menu_add(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@ResponseBody
	@RequestMapping(value = AnRequest.F_MENU_UPDATE, produces = "text/html;charset=UTF-8;")
	public String f_menu_update(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@ResponseBody
	@RequestMapping(value = AnRequest.F_MENU_DELETE, produces = "text/html;charset=UTF-8;")
	public String f_menu_delete(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@ResponseBody
	@RequestMapping(value = AnRequest.F_MENU_QUERY, produces = "text/html;charset=UTF-8;")
	public String f_menu_query(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@RequestMapping(value = AnRequest.F_MENU_ADD_VIEW)
	public ModelAndView f_menu_add_view(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@RequestMapping(value = AnRequest.F_MENU_UPDATE_VIEW)
	public ModelAndView f_menu_update_view(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@RequestMapping(value = AnRequest.F_MENU_DELETE_VIEW)
	public ModelAndView f_menu_delete_view(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@RequestMapping(value = AnRequest.F_MENU_QUERY_VIEW)
	public ModelAndView f_menu_query_view(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

}