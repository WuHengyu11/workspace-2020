package com.wolfcode.mongodb.repository;

import com.wolfcode.mongodb.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 等价于mybatis中的mapper接口
 * spring-data规范
 * 1.创建domain对象接口操作接口,继承MongoRepository
 * 2.接口上需要制定2个泛型,泛型1:操作实体对象User 泛型2:操作对象mongodb的集合中id字段类型
 * 3.在接口上贴上一个标签:@Repository
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByName(String name);
}
