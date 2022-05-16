package com.fdu.mall.service;

import com.fdu.mall.dao.CartMapper;
import com.fdu.mall.model.Cart;
import com.fdu.mall.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;

    @Override
    public int  addToCart(String username, Integer goodsId, Integer num, String specName, String img, String name, String seller, Integer price){
        int i = cartMapper.insertCart(username, goodsId, num, specName, img, name, seller, price);
//        cartMapper.insertCart(username, goodsId, num, specName, "http://wx2.sinaimg.cn/mw600/7dd42f11ly1gx6ph4nfkdj20zk0zk78e.jpg", "name", "seller", (float) 1.0);
        return i;
    }
    @Override
   public  List<Cart> getCartList(String username) {
        List<Cart> cart = cartMapper.selectCartList(username);
        return cart;
    }

    @Override
    public int changeItemNumInCart(String username, Integer goodsId, String specName, Integer num) {
        cartMapper.updateCart(username, goodsId, specName, num);
        return 0;
    }

    @Override
    public int deleteItemFromCart(String username, List<Cart> deleteList) {
        for (Cart cart : deleteList) {
            cartMapper.deleteCart(username, cart.getGoodsId(), cart.getSpecName());
        }
        return 0;
    }

    @Override
    public int settleAccounts(String username, List<Cart> settleList) {

        int totalPrice = 0;
        for (Cart cart : settleList) {
            totalPrice += cart.getPrice() * cart.getNum();
        }
        if (totalPrice > 100)
             return 0;
        for (Cart cart : settleList) {
            cartMapper.deleteCart(username, cart.getGoodsId(), cart.getSpecName());
        }
        return 1;
    }

    //    @Override
//    public int addToCart(String username, Integer goodsId, Integer num, Integer specIndex){
//        return 0;
//    };
//
//    @Override
//    public List<Cart> getCartList(String nickname) {
//        return cartMapper.selectCartList(nickname);
//    }
//
//    @Override
//    public int deletItemFromCart(String nickname, Integer id) {
//        return cartMapper.deletCart(nickname, id);
//    }
//
//    @Override
//    public int changeItemNumInCart(String nickname, Integer id, Integer num) {
//        return cartMapper.changeItemNumInCart(nickname, id, num);
//    }
//
//    @Override
//    public int settleAccounts(String nickname, Integer id) {
//        for (Cart cart : getCartList(nickname))
//        {
//            System.out.println(cart.getId());
//            System.out.println(cart.getNickname());
//            if (cart.getId() == id)
//                cartMapper.deletCart(nickname, id);
//        }
//        return 1;
//    }
}
