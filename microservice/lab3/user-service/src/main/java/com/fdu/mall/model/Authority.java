package com.fdu.mall.model;

public enum Authority {
    USER(3,"买家"),
    SHOP(2,"商家"),
    ADMIN(1,"管理员");


    private Integer authority;
    private String name;

    Authority(Integer authority, String name) {
        this.authority = authority;
        this.name = name;
    }

    public static String getValue(Integer id) {
        Authority[] values = values();
        for (Authority value : values) {
            if(value.authority.equals(id)){
                return value.name;
            }
        }
        return null;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
