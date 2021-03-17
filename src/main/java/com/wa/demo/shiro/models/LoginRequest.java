package com.wa.demo.shiro.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginRequest
 * 2021-03-09 17:34
 *
 * @author wuao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String userName;
    private String password;
}
