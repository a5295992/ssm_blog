
package com.along.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.along.common.BeanAndMapConvert;
import com.along.common.QueryCondition;
import com.along.common.ResultBase;
import com.along.entity.DataEntity;
import com.along.entity.Example;

/**
 * 控制器支持类
 * @author ThinkGem
 * @version 2013-3-23
 */
public abstract class BaseController {
	protected String success = "0:";//成功
	protected String fault 	 = "1:";//失败
	protected String other   = "3:";//其他
	protected String unique  = "500:";//特殊
	/**
	 * 日志对象
	 */
	protected Logger logger = Logger.getLogger(BaseController.class);
	
	protected String pageCount;
	protected String rows;
	protected String sort;
	protected String order;
	
	@Value("#{APP_PROP['jdbc.driver']}")
	protected String adminPath;
	
	protected <F  extends Example<F>> Example<F> getExample(HttpServletRequest request,F f){
		
		int pageCount = QueryCondition.pageCount(request,"rows",10);
		int pageNum = QueryCondition.pageNum(request,"page",0);
		String sort = QueryCondition.pageSort(request,"sort","id");
		sort = toCamalString(sort);
		
		String order = QueryCondition.pageOrder(request,"order","desc");
		
		String groupBy  = QueryCondition.pageGroup(request,"group","id");
		groupBy = toCamalString(groupBy);
		
		f.setPageCount(pageCount);
		f.setPageNum(pageNum);
		f.setGroupByClause(groupBy);
		f.setOrderByClause(sort+" "+order);
		return f;
	}
	
	private String  toCamalString(String sort){
		StringBuilder sb = new StringBuilder();
		for (char sc : sort.toCharArray()) {
			if((sc+"").hashCode()>64&&(sc+"").hashCode()<91){
				sb.append("_"+(sc+"").toLowerCase());
			}else{
				sb.append(sc);
			}
		}
		return sb.toString();
	}
	/**
	 * 客户端返回jsons数据 用于 ajax数返回
	 * @return
	 */
	protected String toJSONString(Object object){
		
		return JSONObject.fromObject(object).toString();
	}
	
	@Autowired
	protected Validator validator;

	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败");
			addMessage(model, list.toArray(new String[]{}));
			return false;
		}
		return true;
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 */
	protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败");
			addMessage(redirectAttributes, list.toArray(new String[]{}));
			return false;
		}
		return true;
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组，不传入此参数时，同@Valid注解验证
	 * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
	 */
	protected void beanValidator(Object object, Class<?>... groups) {
		BeanValidators.validateWithException(validator, object, groups);
	}
	
	/**
	 * 添加Model消息
	 * @param message
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		model.addAttribute("message", sb.toString());
	}
	
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}
	
	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderString(HttpServletResponse response, Object object) {
		return renderString(response, JSONObject.fromObject(object).toString(), "application/json");
	}
	
	/**
	 * 客户端返回字符串
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string, String type) {
		try {
			response.reset();
	        response.setContentType(type);
	        response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public String bindException() {  
        return "error/400";
    }
	
	/**
	 * 授权登录异常
	 */
	@ExceptionHandler({AuthenticationException.class})
    public String authenticationException() {  
        return "error/403";
    }
	
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@SuppressWarnings("deprecation")
			@Override
			public void setAsText(String text) {
				logger.info("text:  "+text);
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				try {
					setValue(DateUtils.parseDate(text));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//			@Override
//			public String getAsText() {
//				Object value = getValue();
//				return value != null ? DateUtils.formatDateTime((Date)value) : "";
//			}
		});
	}
	
	public void validateImage(HttpServletRequest req,
			HttpServletResponse response) throws IOException {
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = req.getSession();
		com.along.common.ValidateCode vCode = new com.along.common.ValidateCode(120, 40, 4, 100);
		session.setAttribute("validateImage", vCode.getCode());
		OutputStream os = response.getOutputStream();
		vCode.write(os);
		os.close();
	}
	
	/**
	 * 
	 * @param <T>
	 * @param <A>
	 * @param list  定义为要验证的数据集合
	 * @param ajaxResult  返回结果集对象
	 * @return 数据验证个数大于0的情况下 返回为true :否则返回为false
	 */
	protected <T extends DataEntity<T>, A extends ResultBase> boolean beanValidatorss(List<T> list, A a,Class<?>... groups) {
		Integer failCount = 0;
		List<T> listValidateSuccess = new ArrayList<T>();
		Set <String> failSet = new HashSet<String>();
		for (T t : list) {
			try{
				BeanValidators.validateWithException(validator, t, groups);
				listValidateSuccess.add(t);//没有异常的数据 我们再添加到  验证成功的数据集合中
			}catch(ConstraintViolationException ex){
				failCount++;
				List<String> failMessage = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
				failMessage.add(0, "数据验证失败");
				failSet.addAll(failMessage);
			}
		}
		a.setFailMessage(new ArrayList<String>(failSet));//添加错误信息到 
		a.setFailCount(failCount);
		list.clear();//把以前的这个数据清空 
		list.addAll(listValidateSuccess);// 重新赋值
		
		return list.size()>0?true:false;//当验证数据 大于0  ...
	}
	
	
	/**
	 * 描述:根据 前台传入的json字符串 (包含 实体信息的数据) 转换为实体的结果集List形式
	 * 前台数据的要求是: json={json1:{entity},json2:{entity}}
	 * @param jsonBeans 从 http 请求发送过来的json字符串 包含对象的信息 
	 * @param clazz      实体的class
	 * @return  为实体的结果集List形式
	 */
	protected  <T> List<T>getBeanListFormJsonString(String jsonBeans,Class<T> clazz) {
		
		@SuppressWarnings("unchecked")
		Map<String,Object> urlEntityss = JSONObject.fromObject(jsonBeans);
		List<T> list = new ArrayList<T>();
		for (Entry<String, Object> urlEntity : urlEntityss.entrySet()) {
			T bean;
			try {
				bean = clazz.newInstance();
				@SuppressWarnings("unchecked")
				Map<String, Object> map = JSONObject.fromObject(urlEntity.getValue());
				BeanAndMapConvert.MapToBean(bean, map);
				list.add(bean);
			} catch (InstantiationException e) {
				logger.error("不能实例化对象"+e);
			} catch (IllegalAccessException e) {
				logger.error("不能实例化对象"+e);
			} catch (Exception e) {
				logger.error("子集无法转化为JSON 确认该字符串是否有双重子集"+e);
			}
		}
		return list;
	}
}
