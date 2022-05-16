package com.fdu.mall.controller;

import com.fdu.mall.model.Result;
import com.fdu.mall.model.Collections;
import com.fdu.mall.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.configurationprocessor.json.JSONException;
import java.io.IOException;

import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/api/mall/order")
public class CollectionController {

    @Autowired
    CollectionService collectionService;


    @PostMapping("getFavList")
    public Result getFavList(@RequestBody Map map){
        List<Collections> cs = collectionService.getCollections(map.get("username").toString()) ;
        return Result.ok(cs);
    }


    @PostMapping("addToFav")
    public Result addcollect(@RequestBody Collections cs) throws NoSuchMethodException, IOException, JSONException {
        collectionService.addcollection(cs.getUsername(), cs.getGoodsId(), cs.getNum(), cs.getSpecName());
        return Result.ok();
    }

    @PostMapping("deleteItemFromFav")
    public Result deletecollect(@RequestParam(value="username") String username,@RequestParam(value="goodsId") int goodsId){
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
