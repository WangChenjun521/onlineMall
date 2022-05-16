package com.fdu.mall.dao;

import com.fdu.mall.model.Collections;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionMapper {
    //收藏
    int addToCollection(@Param("username") String username, @Param("goodsId") Integer goodsId, @Param("num") Integer num, @Param("specName") String specName, @Param("img") String img, @Param("name") String name, @Param("seller") String seller, @Param("price") float price) ;
    Collections selectByKey(@Param("username") String username, @Param("goodsId") int goodsId);
    void deleteToCollection(@Param("username") String username, @Param("goodsId") int goodsId) ;

    List<Collections> getCollections(@Param("username") String username);
}
