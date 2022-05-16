package com.fdu.mall.dao;

import com.fdu.mall.model.Collections;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionMapper {
    //收藏
    void addToCollection(@Param("id_user") int id_user, @Param("id_product") int id_product) ;
    Collections selectByKey(@Param("id_user") int id_user, @Param("id_product") int id_product);
    void deleteToCollection(@Param("id_user") int id_user, @Param("id_product") int id_product) ;

    List<Collections> getCollections(@Param("id_user") int id_user);
}
