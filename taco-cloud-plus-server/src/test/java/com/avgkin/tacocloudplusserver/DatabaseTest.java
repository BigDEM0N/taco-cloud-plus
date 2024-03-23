package com.avgkin.tacocloudplusserver;

import com.avgkin.tacocloudplusserver.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DatabaseTest {
    private UserService userService;
    @Autowired
    public DatabaseTest(UserService userService){
        this.userService = userService;
    }
    @Test
    public void userTest(){
        System.out.println(userService.getById(1));
    }
}
