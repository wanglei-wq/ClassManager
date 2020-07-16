package cn.kgc.service;

import cn.kgc.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2020/3/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    public void login_getAuthList() throws Exception {
        User login = userService.login_getAuthList(new User("15233860813", "ylh218610"));
        if (login != null) {
            System.out.println(login);
        }
    }

}