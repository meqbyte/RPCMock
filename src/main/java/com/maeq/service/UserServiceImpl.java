package com.maeq.service;

import com.maeq.entity.User;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("getUserByUserId, id = " + id );

        String userName = UUID.randomUUID().toString();
        // 如何获取一个随机的boolean
        Random random = new Random();
        Boolean sex = random.nextBoolean();

        return User.builder()
                .userName(userName)
                .sex(sex)
                .id(id)
                .build();
    }

    @Override
    public Integer insertUser(User user) {
        System.out.println("insert user " + user.toString());
        return 1;
    }


}
