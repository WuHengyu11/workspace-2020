package cn.wolfcode.luowowo.search.service.impl;

import cn.wolfcode.luowowo.search.domain.TravelEs;
import cn.wolfcode.luowowo.search.repository.TravelEsRepository;
import cn.wolfcode.luowowo.search.service.ITravelEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelEsServiceImpl implements ITravelEsService {


    @Autowired
    private TravelEsRepository repository;


    @Override
    public void save(TravelEs entity) {
        repository.save(entity);
    }
}
