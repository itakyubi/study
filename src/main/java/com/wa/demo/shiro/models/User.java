package com.wa.demo.shiro.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * User
 * 2021-03-08 16:36
 *
 * @author wuao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String userName;
    private String password;
    private Set<Role> roles;
}
