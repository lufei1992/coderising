package chapter_01.knight.concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience {
	
	// 定义命名的切点
	@Pointcut("execution(* concert.Performance.perform(...))")
	public void performance(){}
	
	// 环绕通知方法
	@Around("performance")
	public void watchPerformance(ProceedingJoinPoint jp){
		try {
			System.out.println("silenceCellPhone");
			System.out.println("takeSeats");
			jp.proceed();
			System.out.println("applause");
		} catch (Throwable e) {			
			System.out.println("demandRefund");
		}
	}
	
	
}
