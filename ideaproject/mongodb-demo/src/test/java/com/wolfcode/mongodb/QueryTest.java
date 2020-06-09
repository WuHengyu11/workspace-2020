package com.wolfcode.mongodb;

import com.wolfcode.mongodb.domain.User;
import com.wolfcode.mongodb.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootTest
public class QueryTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    //需求：查询name=dafei用户信息
    @Test
    public void testQuery(){
        User user = userService.queryByName("dafei");
        System.out.println(user);
    }

    //需求：查询name=dafei用户信息
    @Test
    public void testQuery1(){
        //查询累:可以理解为MQL对象,条件与语句都由她拼接
        Query query = new Query();
        Criteria criteria = Criteria.where("name").is("dafei");
        query.addCriteria(criteria);
        List<User> users = mongoTemplate.find(query, User.class);
        for (User user : users) {
            System.err.println(user);
        }
    }

    //需求: 分页查询所有用户, 每页显示3条,按照id升序排列
    @Test
    public void testQuery2(){
        //查询累:可以理解为MQL对象,条件与语句都由她拼接
        Query query = new Query();
        //分页方式1
        //query.skip(3);
        //query.limit(3);

        //分页方式2 类似于QueryObject 参数1:当前页 参数2:每页多少条
        Pageable pageable = PageRequest.of(2-1, 3);

        //分页
        query.with(pageable);
        //排序
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        query.with(sort);
        List<User> users = mongoTemplate.find(query, User.class);
        System.err.println(users);
    }
    // 查询所有name为dafei或者age<30的文档
    @Test
    public void testQuery3(){
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.orOperator(
                Criteria.where("name").is("dafei"),
                Criteria.where("age").lt(30)
                );
        query.addCriteria(criteria);
        List<User> users = mongoTemplate.find(query, User.class);
        System.err.println(users);
    }

    // 查询所有name含有fei并且30<=age<=32的文档
    @Test
    public void testQuery4(){
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("name").regex(".*fei.*"),
                Criteria.where("age").gte(30).lte(32)
                //Criteria.where("age").lte(32)
        );
        query.addCriteria(criteria);
        List<User> users = mongoTemplate.find(query, User.class);
        System.err.println(users);
    }
}