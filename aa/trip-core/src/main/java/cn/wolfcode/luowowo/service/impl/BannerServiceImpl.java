package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.Banner;
import cn.wolfcode.luowowo.query.BannerQuery;
import cn.wolfcode.luowowo.repository.BannerRepository;
import cn.wolfcode.luowowo.service.IBannerService;
import cn.wolfcode.luowowo.util.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
//@Transactional  暂时先别管事务
public class BannerServiceImpl implements IBannerService {

    @Autowired
    private BannerRepository repository;

    @Autowired
    private MongoTemplate template;


    public Banner get(String id) {
        Optional<Banner> optional = repository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    //page对象里面包括: 总页数 每页显示条数......
    public Page<Banner> query(BannerQuery qo) {

        Query query = new Query();
        return DBHelper.query(template, Banner.class, query,qo.getPageable());
    }

    public void saveOrUpdate(Banner banner) {
        if(!StringUtils.hasLength(banner.getId())){
            //添加
            banner.setId(null);
            repository.save(banner);
        }else{
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(banner.getId()));
            DBHelper.update(template, query, banner,
                    "refId","title",
                    "subTitle","coverUrl",
                    "state","sequence","type");

        }
    }

    public void changeHotValue(String id, int hot) {
        Query query = new Query();
    }

    public void delete(String id) {
        repository.deleteById(id);
    }


    @Override
    public List<Banner> queryBanner(int type) {

        Pageable of = PageRequest.of(0, 5, Sort.Direction.ASC, "sequence");

        return repository.findByTypeAndState(type, Banner.STATE_NORMAL, of);

        //return repository.findByTypeAndStateOrderBySequenceAsc(type, Banner.STATE_NORMAL);
    }
}
