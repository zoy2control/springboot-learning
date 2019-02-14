package com.zoy.springboot.service;

/**
 * Created by zouzp on 2019/2/11.
 */
public interface IUserService {
    /**
     * ·创建用户
     * @param name
     * @param age
     */
    void createUser(String name, Integer age);

    /**
     * ·根据用户名删除用户
     * @param name
     */
    void deleteUser(String name);

    /**
     * ·获取所有用户数目
     * @return
     */
    Integer getAllUsersNum();

    /**
     * ·删除所有用户
     */
    void deleteAllUsers();
}
