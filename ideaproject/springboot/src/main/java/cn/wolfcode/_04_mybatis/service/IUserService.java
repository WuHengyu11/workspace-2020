package cn.wolfcode._04_mybatis.service;

import cn.wolfcode._04_mybatis.domain.User;

public interface IUserService {
    void save(User user);
    User get(Long id);
}
