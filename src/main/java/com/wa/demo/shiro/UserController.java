package com.wa.demo.shiro;

import com.wa.demo.shiro.models.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 * 2021-03-08 16:44
 *
 * @author wuao
 */
@RestController
@Slf4j
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request.getUserName(), request.getPassword());
    }

    @RequiresRoles(value = {"user", "admin"}, logical = Logical.OR)
    @GetMapping("/get")
    public String get() {
        return "get";
    }

    @RequiresPermissions({"add"})
    @GetMapping("/add")
    public String add() {
        return "add";
    }
}
