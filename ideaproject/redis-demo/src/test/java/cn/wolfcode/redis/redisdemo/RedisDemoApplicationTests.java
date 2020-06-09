package cn.wolfcode.redis.redisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RedisDemoApplicationTests {

    /*
    @Test
    void contextLoads() {
        //1.创建连接池
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        //2.从连接池中获取Jedis对象
        Jedis jedis = jedisPool.getResource();
        /* 设置密码
         *jedis.auth(密码);

        //3:TODO
        System.out.println(jedis);
        jedis.set("name","fafei");
        System.out.println(jedis.get("name"));
        //4:关闭资源
        jedis.close();
        jedisPool.destroy();
    }
    */
    @Autowired
    //private RedisTemplate redisTemplate;
    //项目中做约定:key是字符串 value是字符串
    private StringRedisTemplate redisTemplate;
    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("name","dafei");
        System.out.println(redisTemplate.opsForValue().get("name"));
        redisTemplate.opsForValue();//操作字符串
        redisTemplate.opsForHash();//操作hash
        redisTemplate.opsForList();//操作list
        redisTemplate.opsForSet();//操作set
        redisTemplate.opsForZSet();//操作有序set
    }
}
