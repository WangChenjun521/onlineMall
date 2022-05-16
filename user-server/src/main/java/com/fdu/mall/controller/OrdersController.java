package com.fdu.mall.controller;

import com.fdu.mall.model.*;
import com.fdu.mall.service.OrdersService;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("")
public class OrdersController {

    @Autowired
    OrdersService OrdersService;


    //后台接口订单模块1.1:查找该商家除已完成外的所有订单
    @GetMapping("/admin/orderNowAll/{token}")
    public Result OrdersNowAll(@PathVariable("token") String seller){
        List<Content> Ans = OrdersService.OrdersNowAll(seller);
        for (int i = 0;i<Ans.size();i++){
            List<GoodsInSuborders> Temp = OrdersService.bs_find_goods(Ans.get(i).getOrderId());
            if (Temp.size()!=0) Ans.get(i).setChildren(Temp);
            else{
                Ans.remove(i);
                i--;
            }
        }
        return Result.ok(Ans);
    }


    //后台订单模块1.2:更新订单状态
    @PostMapping("/admin/updateOrder")
    public Result updateOrder(@RequestBody Temp temp){
        int i = 0;
        i = OrdersService.updateOrder(temp.content);
        if (i!=0) return Result.ok();
        return Result.fail();
    }


    //后台订单模块1.3:删除订单
    @DeleteMapping("/admin/deleteOrder")
    public Result deleteOrder(@RequestBody Temp temp){
        int i = 0;
        i = OrdersService.deleteOrder(temp.content.getId(), temp.content.getOrderId());
        if(i!=0) return Result.ok();
        return Result.fail();
    }


    //后台订单模块1.4:查找该商家除已完成外的指定订单
    @PostMapping("/admin/orderNowSearch")
    public Result orderNowSearch(@RequestBody Map map){
        String seller = (String) map.get("token");
        String name = (String) map.get("name");
        List<Content> Ans = OrdersService.orderNowSearch(seller, name);
        for (int i = 0;i<Ans.size();i++){
            List<GoodsInSuborders> Temp = OrdersService.bs_find_goods_by_name(Ans.get(i).getOrderId(), name);
            if (Temp.size()!=0) Ans.get(i).setChildren(Temp);
            else{
                Ans.remove(i);
                i--;
            }
        }
        return Result.ok(Ans);
    }


    //后台订单模块2.1:查找该商家的所有订单
    @GetMapping("/admin/orderHistoryAll/{token}")
    public Result orderHistoryAll(@PathVariable("token") String seller){
        List<Content> Ans = OrdersService.orderHistoryAll(seller);
        for (int i = 0;i<Ans.size();i++){
            List<GoodsInSuborders> Temp = OrdersService.bs_find_goods_all(Ans.get(i).getOrderId());
            Ans.get(i).setChildren(Temp);
        }
        return Result.ok(Ans);
    }


    //后台订单模块2.2:查找该商家的指定订单
    @PostMapping("/admin/orderHistorySearch")
    public Result orderHistorySearch(@RequestBody Map map){
        String seller = (String) map.get("token");
        String name = (String) map.get("name");
        List<Content> Ans = OrdersService.orderHistorySearch(seller, name);
        for (int i = 0;i<Ans.size();i++){
            List<GoodsInSuborders> Temp = OrdersService.bs_find_goods_by_name_all(Ans.get(i).getOrderId(), name);
            Ans.get(i).setChildren(Temp);
        }
        return Result.ok(Ans);
    }


    //前台5.1:根据订单状态获取订单
    @PostMapping("/api/mall/order/getOrderByState")
    public Result getOrderByState(@RequestBody Map map){
        String buyer = (String) map.get("username");
        Integer state  = (Integer) map.get("state");
        if (state == -1) {
            List<Orders> AllOrders = OrdersService.getAllStateOrder(buyer);
            for (int i = 0;i<AllOrders.size();i++){
                List<Suborders> Temp1 = OrdersService.findsuborders(AllOrders.get(i).getOrderId());
                for (int j=0;j<Temp1.size();j++){
                    List<GoodsInSuborders> Temp2 = OrdersService.findgoods(Temp1.get(j).getOrderId());
                    Temp1.get(j).setGoods(Temp2);
                }
                AllOrders.get(i).setSubOrders(Temp1);
            }
            return Result.ok(AllOrders);
        }
        List<Orders> OrdersByState = OrdersService.getOrderByState(buyer, state);
        for (int i = 0;i<OrdersByState.size();i++){
            List<Suborders> Temp1 = OrdersService.findsuborders(OrdersByState.get(i).getOrderId());
            for (int j=0;j<Temp1.size();j++){
                List<GoodsInSuborders> Temp2 = OrdersService.findgoods(Temp1.get(j).getOrderId());
                Temp1.get(j).setGoods(Temp2);
            }
            OrdersByState.get(i).setSubOrders(Temp1);
        }
        return Result.ok(OrdersByState);
    }


    //前台5.2:确认付款
    @PostMapping("/api/mall/order/pay")
    public Result pay(@RequestBody Map map){
        Integer id = (Integer) map.get("orderId");
        int i = 0;
        i = OrdersService.pay(id);
        if (i==1) return Result.ok();
        return Result.fail();
    }


    //前台5.3:编辑订单的收货信息
    @PostMapping("/api/mall/order/editOrderInfo")
    public Result editOrderInfo(@RequestBody Map map){
        Integer id = (Integer) map.get("orderId");
        String buyer = (String) map.get("newName");
        Long phone = (Long) map.get("newPhone");
        String address = (String) map.get("newAddress");

        int i = 0;
        i = OrdersService.editOrderInfo(id, buyer, phone, address);
        if (i==1) return Result.ok();
        return Result.fail();
    }


    //前台5.4:取消订单
    @PostMapping("/api/mall/order/cancelOrder")
    public Result cancelOrder(@RequestBody Map map){
        Integer id = (Integer) map.get("orderId");
        int i = 0;
        i = OrdersService.cancelOrder(id);
        if (i==1) return Result.ok();
        return Result.fail();
    }


    //前台5.5:确认收货
    @PostMapping("/api/mall/order/confirmReceive")
    public Result confirmReceive(@RequestBody Map map){
        Integer id = (Integer) map.get("orderId");
        int i = 0;
        i = OrdersService.confirmReceive(id);
        if (i==1) return Result.ok();
        return Result.fail();
    }


    //前台5.6:投诉订单
    @PostMapping("/api/mall/order/complainOrder")
    public Result complainOrder(@RequestBody Map map){
        Integer id = (Integer) map.get("orderId");
        String seller = (String) map.get("username");
        String com_reason = (String) map.get("reason");
        int i = 1;
        //i = OrdersService.complainOrder(id, seller, com_reason);
        if (i==1) return Result.ok();
        return Result.fail();
    }


    //前台6.1：立刻购买（添加订单）
    @PostMapping("/api/mall/order/buyNow")
    public Result buyNow(@RequestBody Map map) throws IOException, JSONException {
        String buyer = (String) map.get("username");
        String goodsId = (String) map.get("goodsId");
        Integer num = (Integer) map.get("num");
        String specName = (String) map.get("specName");

        //调用查询商品信息接口
        URL url = new URL("http://localhost:8085/api/mall/goods/getGoodsInfo");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");
        String data = "{\n  \"goodsId\": \"";
        data += goodsId;
        data +=  "\" \n}";
        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        System.out.println(out);
        OutputStream stream = http.getOutputStream();
        stream.write(out);
        InputStream inputStream = http.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);// 读字符串用
        String inputLine = null;
        String result = "";
        while (((inputLine = reader.readLine()) != null)) {
            result += inputLine + "\n";
        }
        JSONObject jsonObject = new JSONObject(result);
        JSONObject jsonObject1 = new JSONObject(jsonObject.getString("content"));
        String name = jsonObject1.getString("name");
        String img = jsonObject1.getString("img");
        Integer price = jsonObject1.getInt("price");
        String seller = jsonObject1.getString("seller");
        http.disconnect();

        //调用查询用户信息接口
        URL url1 = new URL("http://localhost:8085/api/mall/order/userInfo");
        HttpURLConnection http1 = (HttpURLConnection)url1.openConnection();
        http1.setRequestMethod("POST");
        http1.setDoOutput(true);
        http1.setRequestProperty("Accept", "application/json");
        http1.setRequestProperty("Content-Type", "application/json");
        String data1 = "{\n  \"username\": \"";
        data1 += buyer;
        data1 +=  "\" \n}";
        byte[] out1 = data1.getBytes(StandardCharsets.UTF_8);
        OutputStream stream1 = http1.getOutputStream();
        stream1.write(out1);
        InputStream inputStream1 = http1.getInputStream();
        InputStreamReader inputStreamReader1 = new InputStreamReader(inputStream1);
        BufferedReader reader1 = new BufferedReader(inputStreamReader1);// 读字符串用
        String inputLine1 = null;
        String result1 = "";
        while (((inputLine1 = reader1.readLine()) != null)) {
            result1 += inputLine1 + "\n";
        }
        JSONObject jsonObject2 = new JSONObject(result1);
        String phone = jsonObject2.getString("phone");
        String address = jsonObject2.getString("address");
        http1.disconnect();


        Integer orders_id_max = OrdersService.find_orders_max();
        Integer suborders_id_max = OrdersService.find_suborders_max();
        int i = 1;
        i = OrdersService.buyNow(buyer, Integer.parseInt(goodsId), num, specName, name, img, price, seller, orders_id_max, suborders_id_max, phone, address);
        if (i==1) return Result.ok(i);
        return Result.fail();
    }


//
//
//    //处理投诉
//    @PutMapping("/admin/dealcomOrders")
//    public Result dealcomOrders(@RequestParam Long bid){
//        int i = 0;
//        i = OrdersService.dealcomOrders(bid);
//        if(i==1) return Result.ok("Complain request dealed successfully!");
//        return Result.fail();
//    }
//
//
//    //退款请求
//    @PutMapping("refundOrders")
//    public Result refundOrders(@RequestParam Long bid ){
//        int i = 0;
//        i = OrdersService.refundOrders(bid);
//        if(i==1) return Result.ok("Submit refund request successfully!");
//        return Result.fail();
//    }


}
