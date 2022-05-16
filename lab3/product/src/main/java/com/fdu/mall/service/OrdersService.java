package com.fdu.mall.service;

import com.fdu.mall.dao.OrdersMapper;
import com.fdu.mall.model.Content;
import com.fdu.mall.model.GoodsInSuborders;
import com.fdu.mall.model.Orders;
import com.fdu.mall.model.Suborders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    OrdersMapper OrdersMapper;


    public List<Content> OrdersNowAll(String seller) {
        return  OrdersMapper.OrdersNowAll(seller);
    }
    public List<GoodsInSuborders> bs_find_goods(Long orderId) { return OrdersMapper.bs_find_goods(orderId); };


    public int updateOrder(GoodsInSuborders content) {
        return  OrdersMapper.updateOrder(content.getId(), content.getOrderId(), content.getName(), content.getSpecName(), content.getPrice(), content.getNum(), content.getAmount(), content.getState());
    }


    public int deleteOrder(Long id, Long orderId) {
        return  OrdersMapper.deleteOrder(id, orderId);
    }


    public List<Content> orderNowSearch(String seller, String name) { return OrdersMapper.orderNowSearch(seller, name); }
    public List<GoodsInSuborders> bs_find_goods_by_name(Long orderId, String name) { return OrdersMapper.bs_find_goods_by_name(orderId, name); }


    public List<Content> orderHistoryAll(String seller) {
        return  OrdersMapper.orderHistoryAll(seller);
    }
    public List<GoodsInSuborders> bs_find_goods_all(Long orderId) { return OrdersMapper.bs_find_goods_all(orderId); }


    public List<Content> orderHistorySearch(String seller, String name) { return  OrdersMapper.orderHistorySearch(seller, name); }
    public List<GoodsInSuborders> bs_find_goods_by_name_all(Long orderId, String name) { return OrdersMapper.bs_find_goods_by_name_all(orderId, name); }


    public List<Orders> getAllStateOrder(String buyer) {
        return  OrdersMapper.getAllStateOrder(buyer);
    }
    public List<Suborders> findsuborders(Long fatherorderId) {return OrdersMapper.findsuborders(fatherorderId);}
    public List<GoodsInSuborders> findgoods(Long suborderId) {return OrdersMapper.findgoods(suborderId);}
    public List<Orders> getOrderByState(String buyer, Integer state) {
        return  OrdersMapper.getOrderByState(buyer, state);
    }


    public int pay(Integer id) {
        return OrdersMapper.pay(id);
    }


    public int editOrderInfo(Integer id, String buyer, Long phone, String address) {
        return OrdersMapper.editOrderInfo(id, buyer, phone, address);
    }


    public int cancelOrder(Integer id) {
        return OrdersMapper.cancelOrder(id);
    }


    public int confirmReceive(Integer id) {
        return OrdersMapper.confirmReceive(id);
    }


    public int complainOrder(Integer id, String seller, String com_reason) {
        return OrdersMapper.complainOrder(id, seller, com_reason);
    }


    public Integer find_orders_max(){ return OrdersMapper.find_orders_max(); };
    public Integer find_suborders_max(){ return OrdersMapper.find_suborders_max(); }
    public int buyNow(String buyer, Integer goodsId, Integer num, String specName, String name, String img, Integer price, String seller, Integer orders_id_max, Integer suborders_id_max, String phone, String address) {
        return  OrdersMapper.buyNow(buyer, goodsId, num, specName, name, img, price, seller, orders_id_max, suborders_id_max, phone, address);
    }
//
//
//    public int dealcomOrders(Long bid) {
//        return  OrdersMapper.dealcomOrders(bid);
//    }
//
//
//    public int refundOrders(Long bid) {
//        return  OrdersMapper.refundOrders(bid);
//    }

}