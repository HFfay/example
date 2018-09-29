package com.sinitek.cloud.gateway.filter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class TokenFilter implements GlobalFilter {

    private static Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOGGER.debug("这里进行用户认证、权限控制");

        ServerHttpRequest request = exchange.getRequest();
        List<String> tokens = request.getHeaders().get("accesstoken");
        String accesstoken = null;
        if(tokens != null){
            accesstoken = tokens.get(0);
        }

        if(StringUtils.isNotBlank(accesstoken)){
            if(chechToken(accesstoken)){
                ServerHttpRequest.Builder mutate = request.mutate();
                mutate.header("accesstoken", accesstoken);
                ServerHttpRequest build = mutate.build();
                return chain.filter(exchange.mutate().request(build).build());
            }
        }
        return voidToken(exchange);
    }

    private boolean chechToken(String accesstoken){
        // TODO 添加token验证逻辑，并维护当前用户信息


        return true;
    }


    /**
     * 网关抛异常
     *
     */
    private Mono<Void> voidToken(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        byte[] bytes = new String("{'result':'-1', 'message':'User Token Forbidden or Expired'}").getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

}
