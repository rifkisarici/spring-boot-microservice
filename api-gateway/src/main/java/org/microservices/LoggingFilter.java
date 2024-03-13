package org.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/*@Component
public class LoggingFilter implements GlobalFilter {
    //apiGateway den gecen her isteği loga kaydetmek istersek

    private static final Logger logger= LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        logger.info("rfk Path request recevied -> "+exchange.getRequest().getPath());

        *//*logger.trace("This is a TRACE level log message.");
        logger.debug("This is a DEBUG level log message.");
        logger.info("This is an INFO level log message.");
        logger.warn("This is a WARN level log message.");
        logger.error("This is an ERROR level log message.");*//*

        return chain.filter(exchange);
    }




}*/
