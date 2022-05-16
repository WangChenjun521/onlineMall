package com.fdu.mall.service;

import com.fdu.mall.model.Product;
import com.fdu.mall.dao.ShopMapper;
import com.fdu.mall.model.bo.GoodsContentBO;
import com.fdu.mall.model.vo.GetProductInfoVo;
import com.fdu.mall.model.vo.SearchProductVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ShopService {
    List<Product> getAllGoods();

    List<Product> goodsAll(String seller);

    int addGoods(Product product);

    int updateGoods(GoodsContentBO goods);
    Product getGoodsInfo(Integer id);
    List<SearchProductVO> searchGoods(String keyword);
    int deleteGoods(Integer id);
    GetProductInfoVo getGoodsInfo_front(Integer goodsId);

}
