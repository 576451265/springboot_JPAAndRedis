package com.lh.repository;

import com.lh.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description: 就是mapper
 * extends JpaRepository<User,Long> <实体类型,对应id>
 * @author LuoH
 * @date 2020/01/16
 */
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAll();
}
