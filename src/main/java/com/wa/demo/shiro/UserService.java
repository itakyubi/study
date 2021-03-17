package com.wa.demo.shiro;

import com.wa.demo.shiro.models.Permission;
import com.wa.demo.shiro.models.Role;
import com.wa.demo.shiro.models.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * UserService
 * 2021-03-09 17:00
 *
 * @author wuao
 */
@Service
public class UserService {

    public String login(String userName, String password) {
        // TODO verify username and password
        User user = getUserByName(userName);
        if (!user.getPassword().equals(password)) {
            return "un_auth";
        }

        String token = JwtUtils.sign(userName, password);

        // TODO save token to redis or memory

        return token;
    }

    public User getUserByName(String userName) {
        Permission permission1 = new Permission("1", "query");
        Permission permission2 = new Permission("2", "add");

        Role role1 = new Role("1", "admin", new HashSet<>(Arrays.asList(permission1, permission2)));
        Role role2 = new Role("2", "user", new HashSet<>(Arrays.asList(permission1)));

        User user1 = new User("1", "administrator", "administrator", new HashSet<>(Arrays.asList(role1)));
        User user2 = new User("1", "wuao", "123456", new HashSet<>(Arrays.asList(role2)));


        Map<String, User> map = new HashMap<>();
        map.put(user1.getUserName(), user1);
        map.put(user2.getUserName(), user2);

        return map.get(userName);
    }
}
