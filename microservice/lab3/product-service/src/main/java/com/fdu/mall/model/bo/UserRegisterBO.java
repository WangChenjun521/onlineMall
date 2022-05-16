package com.fdu.mall.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterBO {
    private String username;
    private String pwd;
    private String recipient;
    private String address;
    private String phone;
}
