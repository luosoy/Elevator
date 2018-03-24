package com.luosoy.common.web.interceptor;

import com.luosoy.common.thread.ThreadIdUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		ThreadIdUtil.generateThreadUUId();
		request.setAttribute("ctx", request.getContextPath());
		request.setAttribute("stc", request.getContextPath()+"/assets");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {
		ThreadIdUtil.removeThreadUUId();
	}

}
