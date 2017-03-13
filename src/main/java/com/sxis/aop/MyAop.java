package com.sxis.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class MyAop {

	@Pointcut("execution(* com.sxis.service.imp*.*.check*(..))")
	public void checkLogin() {
	}
	@Pointcut("execution(* com.sxis.service.imp*.*.save*(..))")
	public void saveUser() {
	}

	@Before("checkLogin()")
	public void beforeAdvice(JoinPoint joinPoint) {
		System.out.println("-----beforeAdvice().invoke-----");
		 System.out.println("@Before：目标方法为：" + 
				 joinPoint.getSignature().getDeclaringTypeName() + 
	                "." + joinPoint.getSignature().getName());
		 System.out.println("@Before：参数为：" + Arrays.toString(joinPoint.getArgs()));
		System.out.println("-----End of beforeAdvice()------");
	}

	/**
	 * After 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
	 * 
	 * @param joinPoint
	 */
	@After(value = "checkLogin()")
	public void afterAdvice(JoinPoint joinPoint) {
		 System.out.println("@After：模拟释放资源...");
	        System.out.println("@After：目标方法为：" + 
	        		joinPoint.getSignature().getDeclaringTypeName() + 
	                "." + joinPoint.getSignature().getName());
	        System.out.println("@After：参数为：" + Arrays.toString(joinPoint.getArgs()));
	        System.out.println("@After：被织入的目标对象为：" + joinPoint.getTarget());
	}

	/**
	 * Around 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
	 * 注意：当核心业务抛异常后，立即退出，转向AfterAdvice 执行完AfterAdvice，再转到ThrowingAdvice
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "checkLogin() || saveUser()")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("-----aroundAdvice().invoke-----");
		System.out.println(" 此处可以做类似于Before Advice的事情");
		// 调用核心逻辑
		
		Object[] args = pjp.getArgs();
		 if (args != null && args.length > 0 && args[0].getClass() == String.class) {
	            args[0] = "admin";
	            args[1] = "123";
	        }
		
		Object retVal = pjp.proceed(args);
		System.out.println("此目标是：" + pjp.getTarget());
		
		System.out.println(" 此处可以做类似于After Advice的事情");
		System.out.println("-----End of aroundAdvice()------");
		return retVal;
	}

	/**
	 * AfterReturning 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
	 * 
	 * @param joinPoint
	 */
	@AfterReturning(value = "checkLogin()", returning = "retVal")
	public void afterReturningAdvice(JoinPoint joinPoint, String retVal) {
		System.out.println("-----afterReturningAdvice().invoke-----");
		  System.out.println("@AfterReturning：目标方法为：" + 
				  joinPoint.getSignature().getDeclaringTypeName() + 
	                "." + joinPoint.getSignature().getName());
	        System.out.println("@AfterReturning：参数为：" + 
	                Arrays.toString(joinPoint.getArgs()));
	        System.out.println("@AfterReturning：返回值为：" + retVal);
	        System.out.println("@AfterReturning：被织入的目标对象为：" + joinPoint.getTarget());
		System.out.println("-----End of afterReturningAdvice()------");
	}

	/**
	 * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息
	 * 
	 * 注意：执行顺序在Around Advice之后
	 * 
	 * @param joinPoint
	 * @param ex
	 */
	@AfterThrowing(value = "checkLogin()", throwing = "ex")
	public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
		System.out.println("-----afterThrowingAdvice().invoke-----");
		System.out.println(" 错误信息：" + ex.getMessage());
		System.out.println(" 此处意在执行核心业务逻辑出错时，捕获异常，并可做一些日志记录操作等等");
		System.out.println(" 可通过joinPoint来获取所需要的内容");
		System.out.println("-----End of afterThrowingAdvice()------");
	}

}
