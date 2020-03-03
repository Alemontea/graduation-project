package com.news.annon;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.news.annon.LogAnno;
import com.news.pojo.Logtable;
import com.news.pojo.User;
import com.news.service.LogtableService;

/**
 * @author 杨建 
 * @E-mail: email
 * @version 创建时间：2015-10-19 下午4:29:05
 * @desc 切点类 
 */

@Aspect
@Component
public class LogAopAspect {

    //注入Service用于把日志保存数据库  
    @Autowired  //这里我用resource注解，一般用的是@Autowired，他们的区别如有时间我会在后面的博客中来写
    private LogtableService logtableService;  
    
    private  static  final Logger logger = LoggerFactory.getLogger(LogAopAspect. class);  
    
    //Controller层切点  
    @Pointcut("execution (* com.news.controller..*.*(..))")  
    public  void controllerAspect() {  
    }  
    
    /** 
     * 前置通知 用于拦截Controller层记录用户的操作 
     * 
     * @param joinPoint 切点 
     * @throws Throwable 
     */ 
/*    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("==========执行controller前置通知===============");
        if(logger.isInfoEnabled()){
            logger.info("before " + joinPoint);
        }
    }   */ 
    
    //配置controller环绕通知,使用在方法aspect()上注册的切入点
      @Around("controllerAspect()")
      public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
          System.out.println("==========开始执行controller环绕通知===============");
          Logtable log = new Logtable();
          log.setOperatedate(new Date());  
          /*
           * 获取拦截的实体类
           */
          Object target = joinPoint.getTarget();
          // 拦截的方法名称。当前正在执行的方法
          String methodName = joinPoint.getSignature().getName();
          // 拦截的方法参数
          Object[] args = joinPoint.getArgs();
          
       // 拦截的放参数类型
          Signature sig = joinPoint.getSignature();
          MethodSignature msig = null;
          if (!(sig instanceof MethodSignature)) {
              throw new IllegalArgumentException("该注解只能用于方法");
          }
          msig = (MethodSignature) sig;
          Class[] parameterTypes = msig.getMethod().getParameterTypes();
          Object object = null;
          // 获得被拦截的方法
          Method method = null;
          
          try {
			method = target.getClass().getMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
          if (null != method) {
        	  // 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
        	  if (method.isAnnotationPresent(LogAnno.class)) {
        		  LogAnno logAnno = method.getAnnotation(LogAnno.class);
                  log.setOperateor(logAnno.operationName());
                  log.setOperatetype(logAnno.operationType());
                  try {
                      object = joinPoint.proceed();
                      //long end = System.currentTimeMillis();
                      //log.setRSPONSE_DATA(""+(end-start)); //计算响应时间
                      log.setOperateresult("执行成功！");
                      //保存进数据库
                      System.out.println("执行成功！");
                      logtableService.addLog(log);
                      System.out.println("执行成功2！");
                  } catch (Throwable e) {
                  	// long end = System.currentTimeMillis();
                  	//  log.setRSPONSE_DATA(""+(end-start));
                      log.setOperateresult("执行失败12");
                     logtableService.addLog(log);
                      System.out.println("执行失败！");
                  }
        	  }else {//没有包含注解
                  object = joinPoint.proceed();
              }
        	 
          }else{
        	//不需要拦截直接执行
              object = joinPoint.proceed();
          }
		return object;       
    }
}