package com.lh;

import com.lh.domain.User;
import com.lh.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description:
 * @author LuoH
 * @date 2020/01/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootJpaApplication.class)
public class JpaTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test(){
        List<User> all = userRepository.findAll();
        System.out.println(all);
    }
}
