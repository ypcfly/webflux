package com.ypc.spring.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @Author: ypcfly
 * @Date: 2019/9/21 17:56
 * @Description:
 */
@Component
public class UserFunctionRouter {

    @Autowired
    private UserHandler userHandler;

    @Bean("userRouter")
    public RouterFunction router() {
        RouterFunction routerFunction = route()
                .GET("/user/find/all", accept(MediaType.APPLICATION_JSON_UTF8), userHandler::findAll)
                .GET("/user/query/{uuid}", accept(MediaType.APPLICATION_JSON), userHandler::queryByUUID)
                .POST("/user/add", accept(MediaType.ALL),userHandler::add)
                .PUT("/user/update", accept(MediaType.APPLICATION_JSON_UTF8),userHandler::update)
                .DELETE("/user/delete/{uuid}",accept(MediaType.APPLICATION_JSON_UTF8), userHandler::delete)
                .build();
        return routerFunction;
    }

}
