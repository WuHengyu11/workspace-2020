package cn.wolfcode.service;

import cn.wolfcode.domain.User;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface IUserService {
    void save(User user);
    void delete(Long id);
    void update(User user);
    User get(Long id);
    List<User> listAll();
    PageResult<User> query(QueryObject qo);
}
