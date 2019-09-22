package com.ypc.spring.webflux.service.impl;

import com.ypc.spring.webflux.model.User;
import com.ypc.spring.webflux.service.ReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @Author: ypcfly
 * @Date: 19-9-21 11:55
 * @Description: service
 */
@Slf4j
@Service
public class ReactiveServiceImpl implements ReactiveService {

    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    private static final String USER_KEY = "entity:user";

    @Override
    public Flux<User> findAll() {
        return reactiveRedisTemplate.opsForHash().values(USER_KEY);
    }

    @Override
    public Mono<User> queryByUUID(String name) {
        return reactiveRedisTemplate.opsForHash().get(USER_KEY,name);
    }

    @Override
    public Mono<Boolean> add(User user) {
        String uuid = generateUUID();
        user.setUuid(uuid);
        return reactiveRedisTemplate.opsForHash().put(USER_KEY,user.getUuid(),user);
    }

    private String generateUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    @Override
    public Mono<Boolean> update(User user) {
        return reactiveRedisTemplate.opsForHash().put(USER_KEY,user.getUuid(),user);
    }

    @Override
    public Mono<Boolean> delete(String uuid) {
        return reactiveRedisTemplate.opsForHash().delete(uuid);
    }
}
