package com.luosoy.common.web;


import com.luosoy.common.date.JsonUtils;
import com.luosoy.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionResolver extends AbstractHandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                              Exception ex) {
        ModelAndView model = new ModelAndView();
        LOGGER.error("An exception occurred during request", ex);

        if (WebUtil.isAjaxRequest(request)) {
            try {
                response.setContentType("application/json;charset=UTF-8");
                Response<?> resp = null;
                if (ex instanceof BusinessException) {
                    BusinessException bException = (BusinessException) ex;
                    // 业务异常返回
                    if (!StringUtils.isEmpty(bException.getMessage())) {
                        // 错误信息不为空，则返回错误信息
                        resp = Response.error(bException.getMessage());
                    } else {
                        // 否则返回错误代码
                        resp = Response.error(bException.getErrorCode());
                    }
                } else {
                    resp = Response.error(ex.getMessage());
                }
                response.setStatus(HttpStatus.OK.value());
                JsonUtils.writeValue(response.getWriter(), resp);
            } catch (IOException e) {
                LOGGER.error("Failed to serialize the object to json for exception handling.", e);
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
            model.addObject("exception", ex);
            if (ex instanceof BusinessException) {
                model.setViewName("error/500_biz");
            } else {
                model.setViewName("error/500_com");
            }
        }
        return model;
    }
}
