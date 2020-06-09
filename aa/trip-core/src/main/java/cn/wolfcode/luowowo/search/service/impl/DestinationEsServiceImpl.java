package cn.wolfcode.luowowo.search.service.impl;

import cn.wolfcode.luowowo.repository.DestinationRepository;
import cn.wolfcode.luowowo.search.domain.DestinationEs;
import cn.wolfcode.luowowo.search.repository.DestinationEsRepository;
import cn.wolfcode.luowowo.search.service.IDestinationEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinationEsServiceImpl implements IDestinationEsService {


    @Autowired
    private DestinationEsRepository repository;


    @Override
    public void save(DestinationEs entity) {
        repository.save(entity);
    }

    @Override
    public DestinationEs getByName(String name) {
        return repository.findByName(name);
    }
}
