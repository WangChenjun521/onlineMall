package com.fdu.mall.service;


import com.fdu.mall.dao.CollectionMapper;
import com.fdu.mall.model.Collections;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService{
    @Autowired
    CollectionMapper collectionmapper ;

    @Override
    public List<Collections> getCollections(int idu){
        return collectionmapper.getCollections(idu);
    }


    @Override
    public Collections addcollection(Collections collect) {
        int idu = collect.getId_user();
        int idp = collect.getId_product();
        Collections collection = collectionmapper.selectByKey(idu,idp);

        if(collection == null){
            collectionmapper.addToCollection(idu,idp);
            return collect;
        }

        return null;
    }

    @Override
    public Collections deletecollection(Collections collect) {
        int idu = collect.getId_user();
        int idp = collect.getId_product();
        Collections collection = collectionmapper.selectByKey(idu,idp);
        if(collection != null){
            collectionmapper.deleteToCollection(idu,idp) ;
            return collect;
        }
        return null;
    }
}
