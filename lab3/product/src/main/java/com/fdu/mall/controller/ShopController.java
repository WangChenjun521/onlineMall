package com.fdu.mall.controller;

import com.fdu.mall.model.Cart;
import com.fdu.mall.model.Result;
import com.fdu.mall.model.Product;
import com.fdu.mall.model.bo.GoodsContentBO;
import com.fdu.mall.model.bo.UpdateGoodsBO;
import com.fdu.mall.model.vo.GetProductInfoVo;
import com.fdu.mall.model.vo.SearchProductVO;
import com.fdu.mall.service.AdminService;
import com.fdu.mall.service.ShopService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("")
public class ShopController {

    @Autowired
    ShopService shopService;
//前台接口
    @PostMapping("/api/mall/goods/searchGoods")

    public Result searchGoods(@RequestBody Map map){

        List<SearchProductVO> products = shopService.searchGoods(map.get("keyword").toString());
        if(products==null) return Result.fail("searchGoods failed!");
        else return Result.ok(products);
    }


    @PostMapping("/api/mall/goods/getGoodsInfo")
    public Result getGoodsInfo_front(@RequestBody Map map ){
        String goodsId = (String) map.get("goodsId");

//        GetProductInfoVo product = shopService.getGoodsInfo_front(Integer.getInteger(map.get("goodsId").toString()));
        GetProductInfoVo product = shopService.getGoodsInfo_front(Integer.parseInt(goodsId));

        if(product==null) return Result.fail("getGoodsInfo failed!");
        else return Result.ok(product);
    }

//后台接口
    @GetMapping("/admin/goodsAll/{token}")
    public Result getAllGoods(@PathVariable("token") String seller){
        List<Product> products = shopService.goodsAll(seller);
        return Result.ok(products);
    }
    @PostMapping("/admin/goods/addGoods")
    public Result addGoods(@RequestBody Map map){
        //String name = (String) map.get("name");
        //Integer typeId = (Integer) map.get("typeId");
        //String img = (String) map.get("img");
        //String desc = (String) map.get("desc");
        //List specList = (List) map.get("specList");
        Product product = new Product(null,(String)map.get("name"),(String) map.get("desc"),(String) map.get("img"),(Integer) map.get("price"),(String)map.get("seller"));
        int i = 0;
        i = shopService.addGoods(product);
        if (i==1) return Result.ok(product);
        return Result.fail("add Goods failed!");
    }
    @GetMapping("/admin/deleteGood")
    public Result deleteGoods(@RequestParam("id")Integer id){
        int i = 0;
        i = shopService.deleteGoods(id);
        if(i==1) return Result.ok("Delete goods successfully!");
        return Result.fail("Delete failed!");
    }
    @GetMapping("/admin/goodsSearch")
    public Result getGoodsInfo(@RequestParam("id")Integer id){
        Product product = shopService.getGoodsInfo(id);
        if(product==null) return Result.fail("getGoodsInfo failed!");
        else return Result.ok(product);
    }
    @PostMapping("/admin/updateGood")
    public Result updateGoods(@RequestBody UpdateGoodsBO map){
        int i = 0;

        GoodsContentBO goods= map.getContent();

        System.out.println(goods.getGoodsId());
        i = shopService.updateGoods(goods);
        if (i==1) return Result.ok("update successfully!");
        return Result.fail("update failed!");
    }
}
