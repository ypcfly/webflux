package com.ypc.spring.webflux.repository;

import com.ypc.spring.webflux.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

/**
 * @Author: ypcfly
 * @Date: 2019/9/21 19:11
 * @Description:
 */
@Repository
public class UserRepository {

    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String USER_KEY = "entity:user";

    public Flux<User> findAll() {
        return reactiveRedisTemplate.opsForHash().values(USER_KEY);
    }

    public Mono<User> queryByUUID(String uuid){
        return reactiveRedisTemplate.opsForHash().get(USER_KEY,uuid);
    }

    public Mono<Boolean> add(User user){
        return reactiveRedisTemplate.opsForHash().putIfAbsent(USER_KEY,user.getUuid(),user);
    }

    public Mono<Boolean> update(User user){
        return reactiveRedisTemplate.opsForHash().put(USER_KEY,user.getUuid(),user);
    }

    public Mono<Long> delete(String uuid) {
        return reactiveRedisTemplate.opsForHash().remove(USER_KEY,uuid);
    }

    private String generateUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
