package com.ypc.spring.webflux.controller;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.XmlOneway;
import com.ypc.spring.webflux.model.User;
import com.ypc.spring.webflux.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

/**
 * @Author: ypcfly
 * @Date: 2019/9/21 19:16
 * @Description:
 */
@Slf4j
@Component
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

    public Mono findAll(ServerRequest serverRequest) {
        Flux<User> flux = userRepository.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(flux, User.class);
    }
    // 查询单个
    public Mono queryByUUID(ServerRequest serverRequest) {
        String uuid = serverRequest.pathVariable("uuid");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(userRepository.queryByUUID(uuid),User.class);
    }
    // 添加用户
    public Mono add(ServerRequest serverRequest) {
        // 将请求体转成指定Momo对象
        Mono<User> mono = serverRequest.bodyToMono(User.class);
        User user = createUser(serverRequest);
        return ServerResponse.ok().body(userRepository.add(user),Boolean.class);
    }
    private User createUser(ServerRequest serverRequest) {
        User user = new User();
        Optional<String> userId = serverRequest.queryParam("userId");
        Optional<String> userName = serverRequest.queryParam("userName");
        Optional<String> age = serverRequest.queryParam("age");
        Optional<String> sex = serverRequest.queryParam("sex");
        Optional<String> uuid = serverRequest.queryParam("uuid");
        if (userId.isPresent()) user.setUserId(userId.get());
        if (userName.isPresent()) user.setUserName(userName.get());
        if (age.isPresent()) user.setAge(Integer.valueOf(age.get()));
        if (sex.isPresent()) user.setSex(sex.get());
        if (uuid.isPresent()) {
            user.setUuid(uuid.get());
        } else {
            user.setUuid(generateUUID());
        }
        return user;
    }
    // 更新用户
    public Mono update(ServerRequest serverRequest) {
        Mono<User> mono = serverRequest.bodyToMono(User.class);
        User user = createUser(serverRequest);
        return ServerResponse.ok().body(userRepository.update(user),Boolean.class);
    }
    // 删除用户
    public Mono delete(ServerRequest serverRequest) {
        String uuid = serverRequest.pathVariable("uuid");
        return ServerResponse.ok().body(userRepository.delete(uuid),Long.class);
    }
    private String generateUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
