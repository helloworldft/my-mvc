package com.name.project.demo.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**   
 * @author ft
 * @version 1.0
 * @date 2015年12月8日 下午1:52:15 
 *
 */
@Aspect
public class LogIntercept {

    @Pointcut("execution(public * com.name.project.demo.test.UserAction.queryUsers(..))")
    public void recordLog(){}


    @Around("recordLog()")
    public void doLoggerPointCut(ProceedingJoinPoint pjp) throws Throwable{
    	 this.printLog("已经记录下操作日志@Around 方法执行前");
         pjp.proceed();
         this.printLog("已经记录下操作日志@Around 方法执行后");
    }

    private void printLog(String str){
        System.out.println(str);
    }
    public void doCut(ProceedingJoinPoint pjp)  throws Throwable {
    	
    }
}
