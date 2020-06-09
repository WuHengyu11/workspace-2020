package cn.wolfcode.luowowo.search.service.impl;

import cn.wolfcode.luowowo.search.domain.StrategyEs;
import cn.wolfcode.luowowo.search.repository.StrategyEsRepository;
import cn.wolfcode.luowowo.search.service.IStrategyEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StrategyEsServiceImpl implements IStrategyEsService {


    @Autowired
    private StrategyEsRepository repository;


    @Override
    public void save(StrategyEs entity) {
        repository.save(entity);
    }
}
