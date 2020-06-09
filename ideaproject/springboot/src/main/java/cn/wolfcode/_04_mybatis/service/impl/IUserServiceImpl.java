package cn.wolfcode._04_mybatis.service.impl;

import cn.wolfcode._04_mybatis.domain.User;
import cn.wolfcode._04_mybatis.mapper.UserMapper;
import cn.wolfcode._04_mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    //DQL操作需要只读事务
    @Transactional(readOnly = true)
    public User get(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
