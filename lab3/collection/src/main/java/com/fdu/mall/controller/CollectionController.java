package com.fdu.mall.controller;

import com.fdu.mall.model.Result;
import com.fdu.mall.model.Collections;
import com.fdu.mall.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mall/order")
public class CollectionController {

    @Autowired
    CollectionService collectionService;


    @GetMapping("getFavList")
    public List<Collections> getFavList(@RequestParam(value="id_user") int id_user){
        List<Collections> cs = collectionService.getCollections(id_user) ;
        return cs ;
    }


    @PostMapping("addToFav")
    public Result addcollect(@RequestParam(value="id_user") int id_user,@RequestParam(value="id_product") int id_product){
        Collections c1 = new Collections();
        c1.setId_user(id_user);
        c1.setId_product(id_product);
        System.out.println(id_user);
        Collections ct =  collectionService.addcollection(c1);
        if(ct==null){
            return Result.fail();
        }
        return Result.ok(ct);
    }

    @DeleteMapping("deleteItemFromFav")
    public Result deletecollect(@RequestParam(value="id_user") int id_user,@RequestParam(value="id_product") int id_product){
        Collections c1 = new Collections();
        c1.setId_user(id_user);
        c1.setId_product(id_product);
        Collections ct = collectionService.deletecollection(c1);
        if(ct==null){
            return Result.fail();
        }
        return Result.ok(ct);
    }

}
