package com.fdu.mall.controller;

import com.fdu.mall.model.*;
import com.fdu.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CartController {
//    @Autowired
//    CartService cartService;
//
//    @GetMapping("/mall/order/getCartList")
//    public Result getcartList(@RequestBody Map nickname) {
//        List<Cart> cart = cartService.getCartList((String)nickname.get("token"));
//        return Result.ok(cart);
//    }
//
//    @PostMapping("/mall/order/deleteItemFromCart")
//    public Result deletitemfromcart(@RequestBody Map map) {
//        cartService.deletItemFromCart((String) map.get("token"), (Integer) map.get("itemId"));
//        return Result.ok();
//    }
//
//    @PostMapping("/mall/order/changeItemNumInCart")
//    public Result changeitemnumincart(@RequestBody Map map) {
//        cartService.changeItemNumInCart((String) map.get("token"), (Integer) map.get("itemId"), (Integer) map.get("itemNum"));
//        return Result.ok();
//    }
//
//    @PostMapping("/mall/order/settleAccounts")
//    public Result settleaccounts(@RequestBody Map mp) {
//        List<Cart> carts = (List<Cart>) mp.get("data");
//        for (Cart cart : carts) {
//            System.out.println(cart.getNickname());
//            System.out.println(cart.getId());
//            cartService.settleAccounts(cart.getNickname(), cart.getId());
//        }
//        return Result.ok();
//    }

    @Autowired
    CartService cartService;

    @PostMapping("/mall/order/addToCart")
    public Result addToCart(@RequestBody Cart cart) throws NoSuchMethodException, IOException, JSONException {
        cartService.addToCart(cart.getUsername(), cart.getGoodsId(), cart.getNum(), cart.getSpecName());
        return Result.ok();
    }

    @PostMapping("/mall/order/getCartList")
    public Result getCartList(@RequestBody Map map) {
        List<Cart> cart = cartService.getCartList(map.get("username").toString());
        return Result.ok(cart);
    }

    @PostMapping("/mall/order/deleteItemsFromCart")
    public Result deleteItemsFromCart(@RequestBody CartDeleteParams mp) {
        List<Cart> cartList = new LinkedList<>();
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
        List<Cart> cartList = new LinkedList<>();
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
