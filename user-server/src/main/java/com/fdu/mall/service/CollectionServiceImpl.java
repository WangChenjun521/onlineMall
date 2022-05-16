package com.fdu.mall.service;


import com.fdu.mall.controller.ShopController;
import com.fdu.mall.dao.CollectionMapper;
import com.fdu.mall.model.Collections;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService{
    @Autowired
    CollectionMapper collectionmapper ;

    @Override
    public List<Collections> getCollections(String username){

        return collectionmapper.getCollections(username);
    }


    @Override
    public int addcollection(String username, Integer goodsId, Integer num, String specName) throws NoSuchMethodException, IOException, JSONException {
        ShopController shopController = new ShopController();
        URL url = new URL("http://localhost:8085/api/mall/goods/getGoodsInfo");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\n  \"goodsId\":\"" + goodsId.toString() + "\"\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        while (stream == null)
        {
            stream = http.getOutputStream();
        }
        stream.write(out);
        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String returnValue = "";
        String line;
        while ((line = in.readLine()) != null) {
            returnValue += line;
        }

        JSONObject returnJsonObject = new JSONObject(returnValue);
        JSONObject item = (JSONObject) returnJsonObject.get("content");

        http.disconnect();
        collectionmapper.addToCollection(username, goodsId, num, specName, item.getString("img"), item.getString("name"), "seller1", item.getInt("price"));
        return 0;
    }

    @Override
    public Collections deletecollection(Collections collect) {
        String idu = collect.getUsername();
        int idp = collect.getGoodsId();
        Collections collection = collectionmapper.selectByKey(idu,idp);
        if(collection != null){
            collectionmapper.deleteToCollection(idu,idp) ;
            return collect;
        }
        return null;
    }
}


