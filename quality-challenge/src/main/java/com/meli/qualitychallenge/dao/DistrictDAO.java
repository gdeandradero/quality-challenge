package com.meli.qualitychallenge.dao;

import java.util.HashMap;
import java.util.Map;

public class DistrictDAO {
    public static Map<String, Double> districtMap = new HashMap<>();

    public static void fillMap(){
        districtMap.put("ARARAS", 100.0);
        districtMap.put("BALEIAS", 200.0);
        districtMap.put("CAMELOS", 300.0);
    }

}
