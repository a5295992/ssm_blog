package com.along.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.along.annotation.ControllerLog;
import com.along.annotation.TestParams;
import com.along.common.AjaxResult;
import com.along.common.AnRequest;
import com.along.common.LocationConstant;
import com.along.entity.UrlEntity;
import com.along.entity.UrlEntityExample;
import com.along.entity.UrlEntityExample.Criteria;
import com.along.serviceImpl.UrlEntityServiceImpl;

@Controller
public class UrlEntityController extends BaseController {
	private static Logger log = Logger.getLogger(UrlEntityController.class);
	@Autowired
	private UrlEntityServiceImpl urlEntityServiceImpl;

	@TestParams(params = "{urlEntitys:{}}", methodType = "POST")
	@ControllerLog(description = "后台地址增删改操作")
	@ResponseBody
	@RequestMapping(value = AnRequest.A_URLENTITY_ADD, produces = "text/html;charset=UTF-8;", method = RequestMethod.POST)
	public String a_urlentity_add(HttpServletRequest request,
			HttpServletResponse response) {
		String urlEntitys = WebUtils.getCleanParam(request, "urlEntitys");

		AjaxResult ajaxResult = new AjaxResult();// 操作数据返回结果集
		if (urlEntitys != null) {
			List<UrlEntity> list = getBeanListFormJsonString(urlEntitys,
					UrlEntity.class);// 将字符串集转为对象集

			if (beanValidatorss(list, ajaxResult)) {// 数据验证
				ajaxResult.setSuccessCount(urlEntityServiceImpl.update(list));
			}
		}
		return JSONObject.fromObject(ajaxResult).toString();
	}

	@TestParams(params = "{data:{}}", methodType = "POST")
	@ControllerLog(description = "获取后台地址列表")
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = AnRequest.A_URLENTITY_QUERY, produces = "text/html;charset=UTF-8;")
	public String a_urlentity_query(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		// 参数获取
		String data = WebUtils.getCleanParam(request, "data");
		Map<String, Object> param = (data == null) ? new HashMap<String, Object>()
				: JSONObject.fromObject(data);
		String deleFlag = WebUtils.getCleanParam(request, "delFlag");

		String name     = (String) param.get("name");
		log.info("data :" + data);
		// 封装参数
		UrlEntityExample f = new UrlEntityExample();
		getExample(request, f);
		Criteria criteria = f.createCriteria();

		if (deleFlag != null
				&& (deleFlag.equalsIgnoreCase("1") || deleFlag
						.equalsIgnoreCase("0"))) {
			criteria.andDelFlagIsNotNull();// 删除标记为0
		} else {
			criteria.andDelFlagEqualTo("0");// 删除标记为0
		}
		criteria = name != null ? criteria.andNameLike("%" + name + "%")
				: criteria;

		JSONObject page = urlEntityServiceImpl.getPageToJSONFormatSqlDate(
				f.getPageCount(), f.getPageNum(), f);
		return page == null ? new JSONObject().toString() : page.toString();
	}

	/**
	 * 刷新数据库
	 * 
	 * @return
	 */
	@TestParams(params = "{}", methodType = "GET")
	@ControllerLog(description = "刷新url数据库与实际数据")
	@ResponseBody
	@RequestMapping(value = "/a/urlentity/flushDb", produces = "text/html;charset=UTF-8;")
	public String a_urlentity_flushDb() {
		AjaxResult ajaxResult = new AjaxResult();
		List<UrlEntity> list = null;
		try {
			list = ReadCo.getUrlForm("com.along.controller");
		} catch (ClassNotFoundException e) {
			log.error("包名错误 找不到 文件" + e);
		}
		// 删除原始数据
		UrlEntityExample example = new UrlEntityExample();
		example.createCriteria().andUrlIsNotNull();
		int deleResult = urlEntityServiceImpl.deletes(example);// 删除结果数
		ajaxResult.setDeleCount(deleResult);
		// 更新数据
		int addResult = urlEntityServiceImpl.adds(list);// 更新结果数
		ajaxResult.setAddCount(addResult);

		return toJSONString(ajaxResult);
	}

	@TestParams(params = "{}", methodType = "GET")
	@ControllerLog(description = "进入地址管理")
	@RequestMapping(value = AnRequest.A_URLENTITY_QUERY_VIEW)
	public ModelAndView a_urlentity_query_view(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView(LocationConstant.a_urlentity_query_view);
	}

}