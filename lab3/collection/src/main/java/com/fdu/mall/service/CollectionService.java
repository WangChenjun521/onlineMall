package com.fdu.mall.service;


import com.fdu.mall.model.Collections;

import java.util.List;

public interface CollectionService {
    //收藏
    Collections addcollection(Collections collect);
    Collections deletecollection(Collections collect) ;

    List<Collections> getCollections(int idu);
}
