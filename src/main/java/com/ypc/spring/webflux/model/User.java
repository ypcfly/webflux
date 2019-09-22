package com.ypc.spring.webflux.model;

import lombok.Data;


@Data
public class User {

    private String uuid;

    private Integer age;

    private String userName;

    private String sex;

    private String userId;

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", age=" + age +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}