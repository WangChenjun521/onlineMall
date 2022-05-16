package com.fdu.mall.controller;

import com.fdu.mall.model.*;
import com.fdu.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/mall/order/addToCart")
    public Result addToCart(@RequestBody Map map){
        String buyer = (String) map.get("username");
        Integer goodsId = Integer.parseInt(map.get("goodsId").toString());
        Integer num = (Integer) map.get("num");
        String specName = (String) map.get("specName");

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

        int i = cartService.addToCart(buyer, goodsId, num, specName, img, name, seller, price);
        if(i!=1){
            return Result.fail();
        }
        return Result.ok();
    }

    @PostMapping("/mall/order/getCartList")
    public Result getCartList(@RequestBody Map map) {
        List<Cart> cart = cartService.getCartList(map.get("username").toString());
        return Result.ok(cart);
    }

    @PostMapping("/mall/order/deleteItemsFromCart")
    public Result deleteItemsFromCart(@RequestBody CartDeleteParams mp) {
        List<Cart> cartList = new LinkedList<Cart>();
        for (CartDelete cartDelete : mp.getDeleteList()) {
            Cart cart = new Cart();
            cart.setSpecName(cartDelete.getSpecName());
            cart.setGoodsId(cartDelete.getGoodsId());
            cartList.add(cart);
        }
        cartService.deleteItemFromCart(mp.getUsername(), cartList);

        return Result.ok();
    }

    @PostMapping("/mall/order/changeItemNumInCart")
    public Result changeItemNumInCart(@RequestBody Map mp) {
        cartService.changeItemNumInCart((String) mp.get("username"), (Integer) mp.get("goodsId"), (String) mp.get("specName"), (Integer) mp.get("num"));
        return Result.ok();
    }

    @PostMapping("/mall/order/settleAccounts")
    public Result settleAccounts(@RequestBody CartSettleParams mp) {
        List<Cart> cartList = new LinkedList<Cart>();
        for (CartSettle cartSettle : mp.getSettleList()) {
            Cart cart = new Cart();
            cart.setGoodsId(cartSettle.getGoodsId());
            cart.setSpecName(cartSettle.getSpecName());
            cart.setPrice(cartSettle.getPrice());
            cart.setNum(cartSettle.getNum());
            cartList.add(cart);
        }
        cartService.settleAccounts(mp.getUsername(), cartList);
        return Result.ok();
    }
}
