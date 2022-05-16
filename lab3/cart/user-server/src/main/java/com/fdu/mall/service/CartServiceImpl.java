package com.fdu.mall.service;

import com.fdu.mall.dao.CartMapper;
import com.fdu.mall.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;

    @Override
    public int  addToCart(String username, Integer goodsId, Integer num, String specName) throws NoSuchMethodException, IOException, JSONException {
//        Class<?> product = shopController.getGoodsInfo(goodsId).getData().getClass();
//        Class<Product> product = (Class<Product>) shopController.getGoodsInfo(goodsId).getData().getClass();
        URL url = new URL("http://localhost:8085/api/mall/goods/getGoodsInfo");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\n  \"goodsId\":\"" + goodsId.toString() + "\"\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        while (stream == null)
        {
            stream = http.getOutputStream();
        }
        stream.write(out);
        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String returnValue = "";
        String line;
        while ((line = in.readLine()) != null) {
            returnValue += line;
        }

        JSONObject returnJsonObject = new JSONObject(returnValue);
        JSONObject item = (JSONObject) returnJsonObject.get("content");

//        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
        cartMapper.insertCart(username, goodsId, num, specName, item.getString("img"), item.getString("name"), "seller1", item.getInt("price"));

//        cartMapper.insertCart(username, goodsId, num, specName, "http://wx2.sinaimg.cn/mw600/7dd42f11ly1gx6ph4nfkdj20zk0zk78e.jpg", "name", "seller", (float) 1.0);
        return 0;
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
