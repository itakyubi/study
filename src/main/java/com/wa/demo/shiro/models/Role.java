package com.wa.demo.shiro.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Role
 * 2021-03-08 16:37
 *
 * @author wuao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private String id;
    private String name;
    private Set<Permission> permissions;
}
