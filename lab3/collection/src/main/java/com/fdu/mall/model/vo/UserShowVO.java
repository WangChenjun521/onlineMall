package com.fdu.mall.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserShowVO {
    private Integer id;
    private String username;
   // private String nickname;
    private String pwd;
    private String recipient;
    private String address;
    private String phone;
  //  private Integer authority;
}
