package com.wideoapp.WideoAppGateway.WideoAppGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.filter.GlobalFilter;

@Component
public class LoggingFilter implements GlobalFilter {

    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        logger.info("Request Connection ID" + exchange.getRequest().getId() + " IP " + exchange.getRequest().getRemoteAddress()
                + " Path -> " + exchange.getRequest().getPath());

        logger.info("Response Connection ID" + exchange.getRequest().getId() + " IP " + exchange.getRequest().getRemoteAddress()
                + " status -> " + exchange.getResponse().getStatusCode());

        return chain.filter(exchange);
    }
}
