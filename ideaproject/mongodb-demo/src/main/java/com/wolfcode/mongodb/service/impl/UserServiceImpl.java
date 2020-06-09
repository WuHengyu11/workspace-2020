package com.wolfcode.mongodb.service.impl;

import com.wolfcode.mongodb.domain.User;
import com.wolfcode.mongodb.repository.UserRepository;
import com.wolfcode.mongodb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository repository;
    @Override
    public void save(User user) {
        user.set_id(null);
        repository.save(user);
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public User get(String id) {
        Optional<User> op = repository.findById(id);
        if (op.isPresent()){
            return op.get();
        }
        return null;
    }

    @Override
    public List<User> list() {
        return repository.findAll();
    }

    @Override
    public User queryByName(String name) {
        return repository.findByName(name);
    }
}
