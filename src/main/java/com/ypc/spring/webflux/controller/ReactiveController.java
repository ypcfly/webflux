package com.ypc.spring.webflux.controller;

import com.ypc.spring.webflux.model.User;
import com.ypc.spring.webflux.service.ReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author: ypcfly
 * @Date: 19-9-21 11:50
 * @Description: 和MVC通用的注解开发
 */
@Slf4j
//@RestController
//@RequestMapping("/user")
public class ReactiveController {

    @Autowired
    private ReactiveService reactiveService;
    // 查询所有
    @GetMapping("/find/all")
    public Flux<User> findAll() {
        return reactiveService.findAll();
    }
    // 查询单个
    @GetMapping("/query/{uuid}")
    public Mono<User> queryByName(@PathVariable("uuid") String uuid) {
        return reactiveService.queryByUUID(uuid);
    }
    // 添加用户
    @PostMapping("/add")
    public Mono<Boolean> add(@RequestBody User user) {
       return reactiveService.add(user);
    }
    // 更新用户
    @PutMapping("/update")
    public Mono<Boolean> update(@RequestBody User user) {
        return reactiveService.update(user);
    }
    // 删除用户
    @DeleteMapping("/delete/{uuid}")
    public Mono<Boolean> delete(@PathVariable("uuid") String uuid) {
        return reactiveService.delete(uuid);
    }

}
