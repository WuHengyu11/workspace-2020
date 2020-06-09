package cn.wolfcode.luowowo.search.service.impl;

import cn.wolfcode.luowowo.search.domain.UserInfoEs;
import cn.wolfcode.luowowo.search.repository.UserInfoEsRepository;
import cn.wolfcode.luowowo.search.service.IUserInfoEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoEsServiceImpl implements IUserInfoEsService {


    @Autowired
    private UserInfoEsRepository repository;


    @Override
    public void save(UserInfoEs entity) {
        repository.save(entity);
    }
}
