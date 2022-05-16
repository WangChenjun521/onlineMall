package com.fdu.mall.service;

import com.fdu.mall.dao.AdminMapper;
import com.fdu.mall.dao.ShopMapper;
import com.fdu.mall.model.Authority;
import com.fdu.mall.model.Product;
import com.fdu.mall.model.User;
import com.fdu.mall.model.bo.GoodsContentBO;
import com.fdu.mall.model.vo.GetProductInfoVo;
import com.fdu.mall.model.vo.LoginVO;
import com.fdu.mall.model.vo.SearchProductVO;
import com.fdu.mall.model.vo.UserShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopMapper shopMapper;

    @Override
    public List<Product> getAllGoods(){
        return shopMapper.getAllGoods();
    }

    @Override
    public List<Product> goodsAll(String seller){
        return shopMapper.goodsAll(seller);
    }

    @Override
    public int addGoods(Product product) {
        //shopMapper.insertGoods(product)
        return  shopMapper.addGoods(product);
    }
    @Override
    public int updateGoods(GoodsContentBO goods){return  shopMapper.updateGoods(goods.getGoodsId(),goods.getName(),goods.getPrice(),goods.getImg(),goods.getDesc()); };
    @Override
    public Product getGoodsInfo(Integer id) { return shopMapper.getGoodsInfo(id); }
    @Override
    public int deleteGoods(Integer id) {
        return  shopMapper.deleteGoods(id);
    }
    @Override
    public List<SearchProductVO> searchGoods(String keyword){return  shopMapper.searchGoods(keyword);};
    @Override
    public GetProductInfoVo getGoodsInfo_front(Integer goodsId){return  shopMapper.getGoodsInfo_front(goodsId);  };

}
