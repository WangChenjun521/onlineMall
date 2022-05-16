package com.fdu.mall.dao;

import com.fdu.mall.model.Cart;
import com.fdu.mall.model.Product;
import com.fdu.mall.model.bo.GoodsContentBO;
import com.fdu.mall.model.vo.GetProductInfoVo;
import com.fdu.mall.model.vo.SearchProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopMapper {
    List<Product> getAllGoods();
    // 根据id查商品
    Product getGoodsInfo(@Param("id") Integer id);

    // 增加商品
    int addGoods(@Param("product") Product product);

    // 更新商品
    int updateGoods(@Param("product") Product product);

    // 删除商品·
    int deleteGoods(@Param("id") Integer id);

    List<Product> goodsAll( String seller);

    List<SearchProductVO> searchGoods(String keyword);
    GetProductInfoVo getGoodsInfo_front(Integer goodsId);
    int updateGoods(@Param("goodsId") Integer goodsId,@Param("name") String name, @Param("price") Integer price,@Param("img") String img, @Param("desc") String desc);
}
