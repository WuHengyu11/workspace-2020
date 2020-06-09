package cn.wolfcode.service.impl;

import cn.wolfcode.domain.User;
import cn.wolfcode.mapper.UserMapper;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    //关联mapper接口
    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User get(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> listAll() {
        return userMapper.selectAll();
    }

    @Override
    public PageResult<User> query(QueryObject qo) {
        int count = userMapper.queryForCount(qo);
        if (count == 0){
            return new PageResult<>(qo.getCurrentPage(),qo.getPageSize(),Collections.emptyList(),count);
        }
        List<User> users = userMapper.queryForList(qo);
        return new PageResult<>(qo.getCurrentPage(),qo.getPageSize(),users,count);
    }
}
