package com.fdu.mall.dao;

import com.fdu.mall.model.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
//    // get cart
//    List<Cart> selectCartList(@Param("username") String username);
//    //add to cart
//    int insertCart(@Param("nickname") String nickname, @Param("id") Integer id, @Param("img") String img, @Param("name") String name, @Param("goodsDetailId") Integer goodsDetailId, @Param("spec") String spec, @Param("unitPrice") Float unitPrice, @Param("goodsNum") Integer goodsNum);
//    //delete cart item
//    int deletCart(@Param("nickname") String nickname, @Param("id") Integer id);
//    //change cart item num
//    int changeItemNumInCart(@Param("nickname") String nickname, @Param("id") Integer id, @Param("goodsNum") Integer goodsNum);
    //get Cart
    List<Cart> selectCartList(@Param("username") String username);

    //add to Cart
    int insertCart(@Param("username") String username, @Param("goodsId") Integer goodsId, @Param("num") Integer num, @Param("specName") String specName, @Param("img") String img, @Param("name") String name, @Param("seller") String seller, @Param("price") float price);

    //delet cart item
    int deleteCart(@Param("username") String username, @Param("goodsId") Integer goodsId, @Param("specName") String specName);

    //change item num
    int updateCart(@Param("username") String username, @Param("goodsId") Integer goodsId, @Param("specName") String specName, @Param("num") Integer num);

}
