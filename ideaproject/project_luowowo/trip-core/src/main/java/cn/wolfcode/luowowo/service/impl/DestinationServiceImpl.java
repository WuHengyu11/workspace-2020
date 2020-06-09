package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Region;
import cn.wolfcode.luowowo.query.DestinationQuery;
import cn.wolfcode.luowowo.repository.DestinationRepository;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.IRegionService;
import cn.wolfcode.luowowo.util.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class DestinationServiceImpl implements IDestinationService {
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private IRegionService regionService;
    @Autowired
    private MongoTemplate template;

    @Override
    public void save(Destination destination) {
        destination.setId(null);
        destinationRepository.save(destination);
    }

    @Override
    public void delete(String id) {
        destinationRepository.deleteById(id);
    }

    @Override
    public void update(Destination destination) {
        destinationRepository.save(destination);
    }

    @Override
    public Destination get(String id) {
        Optional<Destination> destination = destinationRepository.findById(id);
        if (destination.isPresent()){
            return destination.get();
        }
        return null;
    }

    @Override
    public List<Destination> listAll() {
        return destinationRepository.findAll();
    }

    @Override
    public List<Destination> queryByIds(List<String> refIds) {

        return destinationRepository.findByIdIn(refIds);
    }

    @Override
    public Page query(DestinationQuery qo) {
        //分页条件对象
        Query query = new Query();
        //条件查询
        if (StringUtils.hasLength(qo.getKeyword())){
            query.addCriteria(Criteria.where("name").regex(".*"+qo.getKeyword()+".*"));
        }
        if (StringUtils.hasLength(qo.getParentId())){
            query.addCriteria(Criteria.where("parentId").is(qo.getParentId()));
        }else {
            query.addCriteria(Criteria.where("parentId").is(null));
        }
        //总记录数
        //long count = template.count(query, Destination.class);
        //if (count == 0){
         //   return Page.empty();
        //}
        //每页显示记录
        Pageable pageable = PageRequest.of(qo.getCurrentPage()-1,qo.getPageSize());
        //query.with(pageable);
       // List<Destination> destinationsList = template.find(query, Destination.class);

        return DBHelper.query(template,Destination.class,query,pageable);
       // return new PageImpl(destinationsList,pageable,count);
    }

    @Override
    public List<Destination> getToasts(String parentId) {
        if (!StringUtils.hasLength(parentId)){
            return Collections.emptyList();
        }
        List<Destination> list = new ArrayList<>();
        /*
        Destination destination = this.get(parentId);//广州
        list.add(destination);
        if (StringUtils.hasLength(destination.getParentId())){
            Destination parent = this.get(destination.getParentId());
            list.add(parent);//广东
            if (StringUtils.hasLength(parent.getParentId())){
                Destination parentP = this.get(destination.getParentId());
                list.add(parent);//中国
            }
        }
         */
        createToasts(list,parentId);
        Collections.reverse(list);
        return list;
    }



    private void createToasts(List<Destination> list,String parentId){
        Destination destination = this.get(parentId);
        list.add(destination);
        if (StringUtils.hasLength(destination.getParentId())){
            createToasts(list,destination.getParentId());
        }
    }

    @Override
    public List<Destination> queryByRegionId(String regionId) {
        List<Destination> list = new ArrayList<>();
        //国内
        if ("-1".equals(regionId)){
            //国内,查询所有省份
            list = destinationRepository.findByParentName("中国");
        }else {
            //其它
            //查询区域关联目的地
            list = regionService.getDestByRegionId(regionId);
        }
        //额外查询字目的地
        for (Destination destination : list) {
            //查询目的地的所有子目的地
            //查询前10个子目的地
            //方案二:查询指定个数的数据,即limit
            Pageable pageable = PageRequest.of(0, 10);
            List<Destination> children = destinationRepository.findByParentId(destination.getId(),pageable);
            //方案一:查询所有,然后截取前10个
            /*
            if (children.size() > 10){
                children = children.subList(0,10);
            }
            */

            destination.setChildren(children);
        }
        return list;
    }
}
