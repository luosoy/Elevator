package com.luosoy.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.Annotation;
import java.util.Arrays;



public abstract class BaseLogAspect {

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseLogAspect.class.getSimpleName());

	/**
	 * Do log.
	 * 
	 * @param pjp
	 *            the pjp
	 * @param type
	 *            the type
	 * @return the object
	 * @throws Throwable
	 *             the throwable
	 */
	protected Object doLog(ProceedingJoinPoint pjp, String type) throws Throwable {// NOSONAR
		Cacheable cacheableAnnon = getAnnontation(pjp, Cacheable.class);
		String logMessage = pjp.getSignature().toShortString();
		boolean isCacheableMethod = (null != cacheableAnnon);
		if (!isCacheableMethod) {
			LOGGER.info(logMessage);
		}
		LOGGER.debug("参数为:{}", Arrays.toString(pjp.getArgs()));
		long start = System.currentTimeMillis();
		try {
			Object result = pjp.proceed();
			long timecost = System.currentTimeMillis() - start;
			LOGGER.info("{}:成功![timecost:{}]", logMessage, timecost);
			return result;
		} catch (Exception e) {
			long timecost = System.currentTimeMillis() - start;
			LOGGER.warn("{}:失败! [timecost:{}] 异常为{}:{}", logMessage, timecost, e.getClass().getSimpleName(),
					e.getMessage());
			throw e;
		}
	}

	/**
	 * Whether method is CACHEABLE.
	 * 
	 * @param pjp
	 * @return
	 */
	private <T extends Annotation> T getAnnontation(ProceedingJoinPoint pjp, Class<T> annotationClass) {
		try {
			if (pjp.getSignature() instanceof MethodSignature) {
				MethodSignature ms = (MethodSignature) pjp.getSignature();
				T annon = ms.getMethod().getAnnotation(annotationClass);
				if (null != annon) {
					return annon;
				}
			}
		} catch (Exception e) {
			LOGGER.error("日志打印失败!", e);
		}
		return null;
	}
}