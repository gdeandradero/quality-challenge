package com.meli.qualitychallenge.dao;

import java.util.HashMap;
import java.util.Map;

public class DistrictDAO {
    public static Map<String, Double> districtMap = new HashMap<>();

    public static void fillMap(){
        districtMap.put("YELLOW DISTRICT", 100.0);
        districtMap.put("BLUE DISTRICT", 200.0);
        districtMap.put("WHITE DISTRICT", 300.0);
    }

}
