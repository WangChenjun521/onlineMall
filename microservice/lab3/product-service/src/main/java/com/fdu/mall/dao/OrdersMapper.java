package com.fdu.mall.dao;

import com.fdu.mall.model.Content;
import com.fdu.mall.model.GoodsInSuborders;
import com.fdu.mall.model.Orders;
import com.fdu.mall.model.Suborders;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrdersMapper {
    //后台订单模块1.1:查询该商家除已完成外的所有订单
    List<Content> OrdersNowAll(@Param("seller") String seller);
    List<GoodsInSuborders> bs_find_goods(@Param("orderId") Long orderId);


    //后台订单模块1.2:更新订单
    int updateOrder(@Param("id") Long id, @Param("orderId") Long orderId, @Param("name") String name, @Param("specName") String specName, @Param("price") Long price, @Param("num") Long num, @Param("amount") Long amount, @Param("state") Long state);


    //后台订单模块1.3:删除订单
    int deleteOrder(@Param("id") Long id, @Param("orderId") Long orderId);


    //后台订单模块1.4:查找该商家除已完成外的指定订单
    List<Content> orderNowSearch(@Param("seller") String seller, @Param("name") String name);
    List<GoodsInSuborders> bs_find_goods_by_name(@Param("orderId") Long orderId, @Param("name") String name);


    //后台订单模块2.1:查询该商家的所有订单
    List<Content> orderHistoryAll(@Param("seller") String seller);
    List<GoodsInSuborders> bs_find_goods_all(@Param("orderId") Long orderId);


    //后台订单模块2.2:查找该商家指定订单
    List<Content> orderHistorySearch(@Param("seller") String seller, @Param("name") String name);
    List<GoodsInSuborders> bs_find_goods_by_name_all(@Param("orderId") Long orderId, @Param("name") String name);


    //前台5.1:查找全部订单
    List<Orders> getAllStateOrder(@Param("buyer") String buyer);
    List<Suborders> findsuborders(@Param("fatherorderId") Long fatherorderId);
    List<GoodsInSuborders> findgoods(@Param("orderId") Long orderId);
    List<Orders> getOrderByState(@Param("buyer") String buyer, @Param("state") Integer state);


    //前台5.2：确认付款
    int pay(@Param("orderId") Integer orderId);


    //前台5.3:更新订单信息
    int editOrderInfo(@Param("orderId") Integer orderId, @Param("buyer") String buyer, @Param("phone") Long phone, @Param("address") String address);


    //前台5.4:取消订单
    int cancelOrder(@Param("orderId") Integer orderId);


    //前台5.5:确认收货
    int confirmReceive(@Param("orderId") Integer orderId);


    //前台5.6:投诉订单
    int complainOrder(@Param("orderId") Integer orderId, @Param("seller") String seller, @Param("com_reason") String com_reason);


    //前台6.1:立即购买
    Integer find_orders_max();
    Integer find_suborders_max();
    int buyNow(@Param("buyer") String buyer, @Param("goodsId") Integer goodsId, @Param("num") Integer num, @Param("specName") String specName, @Param("name") String name, @Param("img") String img, @Param("price") Integer price, @Param("seller") String seller, @Param("orders_id_max") Integer orders_id_max, @Param("suborders_id_max") Integer suborders_id_max, @Param("phone") String phone, @Param("address") String address);
//
//
//    int dealcomOrders(@Param("bid") Long bid);
//
//
//    int refundOrders(@Param("bid") Long bid);

}
