package com.fdu.mall.service;


import com.fdu.mall.model.Collections;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface CollectionService {
    //收藏
    int addcollection(String username, Integer goodsId, Integer num, String specName) throws NoSuchMethodException, IOException, JSONException;
    Collections deletecollection(Collections collect) ;

    List<Collections> getCollections(String username);
}
