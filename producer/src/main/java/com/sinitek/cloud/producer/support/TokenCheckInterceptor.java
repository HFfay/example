package com.sinitek.cloud.producer.support;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenCheckInterceptor implements HandlerInterceptor {

    private static Logger LOGGER = LoggerFactory.getLogger(TokenCheckInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("这里进行token验证，用于微服务间安全调用、获取当前用户");

        String accesstoken = request.getHeader("accesstoken");
        LOGGER.debug("accesstoken = {}", accesstoken);
        if(StringUtils.isNotBlank(accesstoken)){

            // TODO 添加token验证逻辑，并维护当前用户信息

            return true;
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
