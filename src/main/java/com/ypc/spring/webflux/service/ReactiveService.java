package com.ypc.spring.webflux.service;

import com.ypc.spring.webflux.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author: ypcfly
 * @Date: 19-9-21 11:55
 * @Description:
 */
public interface ReactiveService {

    Flux<User> findAll();

    Mono<User> queryByUUID(String name);

    Mono<Boolean> add(User user);

    Mono<Boolean> update(User user);

    Mono<Boolean> delete(String uuid);
}
