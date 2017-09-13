package com.along.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.along.annotation.ControllerLog;
import com.along.annotation.ServiceLog;
import com.along.common.DateUtil;
import com.along.common.UserUtils;
import com.along.entity.Log;
import com.along.security.ShiroUser;
import com.along.serviceImpl.LogServiceImpl;
@Component
@Aspect
@Order(1)
public class LoggerProxy {
	private static Logger logger = Logger.getLogger(LoggerProxy.class);
	private long start = System.currentTimeMillis()-2;
	@Autowired
	private LogServiceImpl  logService;
	private static Log log = new Log();
	@Pointcut("@annotation(com.along.annotation.ServiceLog)") 
	public void serviceAspect(){
		
	}
	//控制层 日志
	@Pointcut("@annotation(com.along.annotation.ControllerLog)") 
	public void controllerAspect(){
		
	}
	
    @Before("controllerAspect()")    
    public  void doBefore(JoinPoint joinPoint) {    
       start = System.currentTimeMillis();
       HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
       System.out.println("请求http"+request);
       //读取session中的用户    
       ShiroUser user = UserUtils.getUser();
       String userName = (user==null)?"匿名用户":user.getLoginName();
       //请求的IP    
       String ip = request.getRemoteAddr();    
        try {    
           //*========控制台输出=========*//    
           System.out.println("=====前置通知开始=====");    
           System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
           System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));    
           System.out.println("请求人:" + userName);    
           System.out.println("请求IP:" + ip); 
           System.out.println("请求地址:"+request.getRequestURI());
           //*========数据库日志=========*//    
           log.setDescription(getControllerMethodDescription(joinPoint)); 
           log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
           log.setRequestIp(ip);    
           log.setExceptionCode( null);    
           log.setExceptionDetail( null);    
           log.setCreateBy(userName);    
           log.setCreateDate(DateUtil.toSqlDate()); 
           log.setUrl(request.getRequestURI());
           //保存数据库    
           System.out.println("=====前置通知结束=====");    
       }  catch (Exception e) {    
           //记录本地异常日志    
           logger.error("==前置通知异常==");    
           logger.error(e);    
       }    
   }    
    @After("controllerAspect()")    
    public  void afterD(){
    	log.setTime(System.currentTimeMillis()-start);
    	try {
			logService.add(log);
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
   /**  
    * 异常通知 用于拦截service层记录异常日志  
    *  
    * @param joinPoint  
    * @param e  
    */    
   @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")    
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {    
       HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
       //读取session中的用户    
       ShiroUser user = UserUtils.getUser();
       String userName = (user==null)?"匿名用户":user.getLoginName();
       //获取请求ip    
       String ip = request.getRemoteAddr();    
       //获取用户请求方法的参数并序列化为JSON格式字符串    
        try {    
             /*========控制台输出=========*/    
           System.out.println("=====异常通知开始=====");    
           System.out.println("异常代码:" + e.getClass().getName());    
           System.out.println("异常信息:" + e.getMessage());    
           System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
           System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));    
           System.out.println("请求人:" + userName);    
           System.out.println("请求IP:" + ip);    
              /*==========数据库日志=========*/    
           log.setDescription(getServiceMthodDescription(joinPoint));    
           log.setExceptionCode(e.getClass().getName());    
           log.setExceptionDetail(e.getMessage());    
           log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
           log.setCreateBy(userName);    
           log.setCreateDate(DateUtil.toSqlDate());    
           log.setRequestIp(ip);
           log.setUrl(request.getRequestURI());
           //保存数据库    
           logService.add(log);    
           System.out.println("=====异常通知结束=====");    
       }  catch (Exception ex) {    
           //记录本地异常日志    
           logger.error("==异常通知异常==");    
           logger.error("异常信息:"+ ex.getMessage());    
       }    
        /*==========记录本地异常日志==========*/    
       logger.error(
    		   	" 异常方法:"+joinPoint.getTarget().getClass().getName()
    		   +" 异常代码:"+e.toString()
    		   +" 异常信息:"+e.getClass().getName()
    		   +" 参数:"+e.getMessage());    
   
   }    
   
   
   /**  
    * 获取注解中对方法的描述信息 用于service层注解  
    *  
    * @param joinPoint 切点  
    * @return 方法描述  
    * @throws Exception  
    */    
    public  static String getServiceMthodDescription(JoinPoint joinPoint)    
            throws Exception {    
       String targetName = joinPoint.getTarget().getClass().getName();    
       String methodName = joinPoint.getSignature().getName();    
       Object[] arguments = joinPoint.getArgs();    
       Class<?> targetClass = Class.forName(targetName);    
       Method[] methods = targetClass.getMethods();    
       String description = "";    
        for (Method method : methods) {    
            if (method.getName().equals(methodName)) {    
               Class<?>[] clazzs = method.getParameterTypes();    
                if (clazzs.length == arguments.length) {    
                   description = method.getAnnotation(ServiceLog. class).description();    
                    break;    
               }    
           }    
       }    
        return description;    
   }    
   
   /**  
    * 获取注解中对方法的描述信息 用于Controller层注解  
    *  
    * @param joinPoint 切点  
    * @return 方法描述  
    * @throws Exception  
    */    
    public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
       String targetName = joinPoint.getTarget().getClass().getName();
       String methodName = joinPoint.getSignature().getName();    
       Object[] arguments = joinPoint.getArgs();    
       Class<?> targetClass = Class.forName(targetName);  
       Method[] methods = targetClass.getMethods();    
       String description = "";    
        for (Method method : methods) {    
            if (method.getName().equals(methodName)) {    
               Class<?>[] clazzs = method.getParameterTypes();    
                if (clazzs.length == arguments.length) {    
                   description = method.getAnnotation(ControllerLog. class).description();    
                    break;    
               }    
           }    
       }    
        return description;    
        
   }    
}    

