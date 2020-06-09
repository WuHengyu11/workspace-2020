package com.wolfcode.mongodb.service;

import com.wolfcode.mongodb.domain.User;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * 用户服务接口
 */
public interface IUserService {
    void save(User user);
    void update(User user);
    void delete(String id);
    User get(String id);
    List<User> list();

    /**
     * 通过名字查询用户信息
     * @param name
     * @return
     */
    User queryByName(String name);
}
