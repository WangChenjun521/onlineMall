package com.fdu.mall.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGoodsBO {
    private String token;
    private GoodsContentBO content;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public GoodsContentBO getContent() {
        return content;
    }

    public void setContent(GoodsContentBO content) {
        this.content = content;
    }
}
