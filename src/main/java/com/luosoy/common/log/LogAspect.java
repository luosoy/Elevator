package com.luosoy.common.log;

import com.luosoy.common.aspect.BaseLogAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Order(5)
public class LogAspect extends BaseLogAspect {

	@Around("execution(public * com.luosoy.*.controller.*.*(..))")
	public Object logController(ProceedingJoinPoint pjp) throws Throwable {
		return doLog(pjp, "Controller");
	}

	@Around("execution(public * com.luosoy.*.service.*.*(..))")
	public Object logService(ProceedingJoinPoint pjp) throws Throwable {
		return doLog(pjp, "Service");
	}

	@Around("execution(public * com.luosoy.*.facade.*.*(..))")
	public Object logFacade(ProceedingJoinPoint pjp) throws Throwable {
		return doLog(pjp, "Facade");
	}
}
