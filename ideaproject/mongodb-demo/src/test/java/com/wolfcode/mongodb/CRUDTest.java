package com.wolfcode.mongodb;

import com.wolfcode.mongodb.domain.User;
import com.wolfcode.mongodb.service.IUserService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

//@SpringBootTest //ps:必须加上启动类
//public class CRUDTest {
//
//    @Autowired
//    private IUserService userService;
//    @Test //Junit5
//    public void testSave(){
//        User user = new User();
//        user.setName("关羽");
//        user.setAge(55);
//        user.setHobby(Arrays.asList("c","c++"));
//        userService.save(user);
//    }
//
//    @Test //Junit5
//    public void testUpdate(){
////        User user = new User();
////        user.set_id(new ObjectId("5eb642bc2c0fa56b0a289957"));
////        user.setId(15L);
////        user.setName("刘备");
////        user.setAge(100);
////        user.setHobby(Arrays.asList("c","c++"));
//         //1.先查
//        User user = userService.get(new ObjectId("5eb642bc2c0fa56b0a289957"));
//        //2.替换
//        user.setName("关羽1");
//        //3.
//        userService.update(user);
//    }
//
//    @Test //Junit5
//    public void testDelete(){
//       userService.delete(new ObjectId("5eb64241c88c006fbb70d338"));
//    }
//
//    @Test //Junit5
//    public void testGet(){
//        User user = userService.get(new ObjectId("5eb642bc2c0fa56b0a289957"));
//        System.err.println(user);
//    }
//
//    @Test //Junit5
//    public void testList(){
//        List<User> list = userService.list();
//        list.forEach(System.out::println);
//    }
//}
