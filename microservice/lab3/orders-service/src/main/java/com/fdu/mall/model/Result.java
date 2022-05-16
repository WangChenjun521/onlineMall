package com.fdu.mall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    Boolean success;//自己的前后端应用 ： 自定义的状态码 → 通常前端根据该状态码做不同的处理
    Integer code;
    T content;
    T data;
    String message;//告诉前端请求的消息

    public static Result ok(){
        Result<Object> baseRespVo = new Result<>();
        baseRespVo.setSuccess(true);
        baseRespVo.setCode(0);
        baseRespVo.setMessage("");
        return baseRespVo;
    }
    public static Result ok(Object data){
        Result<Object> baseRespVo = new Result<>();
        baseRespVo.setData(data);
        baseRespVo.setContent(data);
        baseRespVo.setSuccess(true);
        baseRespVo.setCode(0);
        baseRespVo.setMessage("");
        return baseRespVo;
    }
    public static Result ok(Object data, String message){
        Result<Object> baseRespVo = new Result<>();
        baseRespVo.setContent(data);
        baseRespVo.setData(data);
        baseRespVo.setSuccess(true);
        baseRespVo.setCode(0);
        baseRespVo.setMessage(message);
        return baseRespVo;
    }
    public static Result fail(){
        Result<Object> baseRespVo = new Result<>();
        baseRespVo.setSuccess(false);
        baseRespVo.setCode(10000);
        baseRespVo.setMessage("失败");
        return baseRespVo;
    }
    public static Result fail(String message){
        Result<Object> baseRespVo = new Result<>();
        baseRespVo.setSuccess(false);
        baseRespVo.setCode(10000);
        baseRespVo.setMessage(message);
        return baseRespVo;
    }
}
