package com.fdu.mall.service;


import com.fdu.mall.model.Collections;
//import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface CollectionService {
    //收藏

    int addcollection(String buyer, Integer goodsId,Integer num,String specName,String img,String name,String seller,Integer price );
    Collections deletecollection(Collections collect) ;

    List<Collections> getCollections(String username);

}
