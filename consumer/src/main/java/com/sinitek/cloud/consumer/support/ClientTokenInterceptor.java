package com.sinitek.cloud.consumer.support;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class ClientTokenInterceptor implements RequestInterceptor {

    private static Logger LOGGER = LoggerFactory.getLogger(ClientTokenInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // TODO 获取当前线程的request，传递header，以便后续调用的微服务接口能够获取token

//        requestTemplate.header("accesstoken", "asdasdsad");
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    if(StringUtils.equalsIgnoreCase(name, "accesstoken")){
                        LOGGER.debug("设置feign请求的token[{}]", values);
                        requestTemplate.header("accesstoken", values + "");
                        break;
                    }
                }
            }
        } catch (Exception e){
            LOGGER.error("设置feign请求的header失败", e);
        }
    }
}
