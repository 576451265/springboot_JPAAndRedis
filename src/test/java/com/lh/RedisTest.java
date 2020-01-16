package com.lh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lh.domain.User;
import com.lh.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description: 集成Redis
 * @author LuoH
 * @date 2020/01/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootJpaApplication.class)
public class RedisTest {

    //注入模板
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //jpa
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws JsonProcessingException {
        //1、从redis中获取数据 数据形式json
        String userListJson = redisTemplate.boundValueOps("user.findAll").get();
        //2、判断redis中是否存在数据
        if(null == userListJson){
            //3、不存在数据，从数据库查询
            List<User> all = userRepository.findAll();
            //4、将查询出的数据存储到redis缓存中
            //向将list集合转换成json格式的字符串，使用Jackson进行转换
            ObjectMapper objectMapper = new ObjectMapper();
            userListJson = objectMapper.writeValueAsString(all);
            redisTemplate.boundValueOps("user.findAll").set(userListJson);
            System.out.println("========从数据库中获取user数据=======");
        }else {
            System.out.println("========从redis缓存中获取user数据=======");
        }

        //4、将数据在控制台打印
        System.out.println(userListJson);
    }
}
