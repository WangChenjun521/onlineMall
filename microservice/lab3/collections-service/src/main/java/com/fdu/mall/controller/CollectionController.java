package com.fdu.mall.controller;

import com.fdu.mall.model.Result;
import com.fdu.mall.model.Collections;
import com.fdu.mall.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestOperations;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/api/mall/order")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @Autowired
    RestTemplate restTemplate;


    @PostMapping("getFavList")
    public Result getFavList(@RequestBody Map map){
        List<Collections> cs = collectionService.getCollections(map.get("username").toString()) ;
        return Result.ok(cs);
    }


    @PostMapping("addToFav")
    public Result addcollect(@RequestBody Map map){
        String buyer = map.get("username").toString();
        Integer goodsId=Integer.parseInt(map.get("goodsId").toString()) ;

        String url="http://product-service/api/mall/goods/getGoodsInfo";
        //        设置请求头，请求类型为json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

//        设置请求参数
        HashMap<String, Object> ma = new HashMap<>();
        ma.put("goodsId", goodsId);

        //用HttpEntity封装整个请求报文
        HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(ma, headers);
        Result result = restTemplate.postForObject(url, request, Result.class);
        HashMap data = (HashMap) result.getData();
        String name = ((String) data.get("name"));
        String img = ((String) data.get("img"));
        Integer price = ((Integer) data.get("price"));
        String seller = ((String) data.get("seller"));



        int i = 1;
        i = collectionService.addcollection(buyer,goodsId,1,"pro",img,name,seller,price);
        if (i==1) return Result.ok(i);
        return Result.fail();
    }

    @PostMapping("deleteItemFromFav")
    public Result deletecollect(@RequestBody Map map ){

        String username=map.get("username").toString();
        Integer goodsId=Integer.parseInt(map.get("goodsId").toString()) ;
        Collections c1 = new Collections();
        c1.setUsername(username);
        c1.setGoodsId(goodsId);
        Collections ct = collectionService.deletecollection(c1);
        if(ct==null){
            return Result.fail();
        }
        return Result.ok(ct);
    }

}
