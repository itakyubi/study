package com.wa.demo.auth;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 * 2021-03-04 16:05
 *
 * @author wuao
 */
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @GetMapping("/read")
    public String read() {
        return "read";
    }

    @Auth(permission = "READ")
    @GetMapping("/readwithauth")
    public String readWithAuth() {
        return "read";
    }

    @Auth(role = {"ADMIN", "USER"}, permission = "ADD")
    @PostMapping("/add")
    public String add() {
        return "add";
    }

    @Auth(role = "ADMIN", permission = "DELETE")
    @DeleteMapping("/delete")
    public String delete() {
        return "delete";
    }
}
