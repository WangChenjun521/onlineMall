package com.fdu.mall.service;

import com.fdu.mall.model.Cart;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.List;

public interface CartService {
    //4.1 addToCart
    int addToCart(String username, Integer goodsId, Integer num, String specName) throws NoSuchMethodException, IOException, JSONException;

    //4.2 getCartList
    List<Cart> getCartList(String username);

    //4.3 deleteItemsFromCart
    int deleteItemFromCart(String username, List<Cart> deleteList);

    //4.4 changeItemNumInCart
    int changeItemNumInCart(String username, Integer goodsId, String specName, Integer num);
    
    //4.5 settleAccounts
    int settleAccounts(String username, List<Cart> settleList);
}
