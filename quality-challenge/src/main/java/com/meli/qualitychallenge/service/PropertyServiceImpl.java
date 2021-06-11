package com.meli.qualitychallenge.service;

import com.meli.qualitychallenge.dao.DistrictDAO;
import com.meli.qualitychallenge.exceptions.DistrictError;
import com.meli.qualitychallenge.models.Property;
import com.meli.qualitychallenge.models.Room;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class PropertyServiceImpl implements PropertyService {

    /*
     * US 0001 - totalSquareMeterProperty
     */
    @Override
    public ResponseEntity<Double> totalSquareMeterProperty(Property property) {
        if (property == null || property.getRoomList() == null){
            return new ResponseEntity<>(0.0, HttpStatus.BAD_REQUEST);
        }
        Double result = totalSquareMeterPropertyCalc(property);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /*
     * US 0002 - totalPriceProperty
     */
    @Override
    public ResponseEntity<Double> totalPriceProperty(Property property) {
        if (property == null || property.getRoomList() == null){
            return new ResponseEntity<>(0.0, HttpStatus.BAD_REQUEST);
        }
        Double priceDistrict = getDistrictPrice(property);
        Double sizeProperty = totalSquareMeterPropertyCalc(property);
        Double totalPrice = sizeProperty * priceDistrict;
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    public Double getDistrictPrice(Property property){
        DistrictDAO.fillMap();
        if (!DistrictDAO
                .districtMap
                .containsKey(property.getDistrict().toUpperCase())){
            throw new DistrictError();
        }
        return DistrictDAO
                .districtMap
                .get(property.getDistrict().toUpperCase());
    }

    /*
     * US 0003 - biggestRoom
     */
    @Override
    public ResponseEntity<Room> biggestRoom(Property property) {
        if (property == null || property.getRoomList() == null){
            return new ResponseEntity<>(new Room(), HttpStatus.BAD_REQUEST);
        }
        Room biggestRoomAttribute = new Room();
        biggestRoomAttribute.setWidth(0.0);
        biggestRoomAttribute.setLenght(0.0);
        for (Room room : property.getRoomList()){
            if (totalSquareMeterRoom(room) > totalSquareMeterRoom(biggestRoomAttribute)){
                biggestRoomAttribute = room;
            }
        }
        return new ResponseEntity<>(biggestRoomAttribute, HttpStatus.OK);
    }

    /*
     * US 0004 - totalSquareMeterRooms
     */
    @Override
    public ResponseEntity<Map<String, Double>> totalSquareMeterRooms(Property property) {
        if (property == null || property.getRoomList() == null){
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.BAD_REQUEST);
        }
        Map<String, Double> resultMap = new HashMap<>();
        for (Room room : property.getRoomList()){
            resultMap.put(room.getName(), totalSquareMeterRoom(room));
        }
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    private Double totalSquareMeterPropertyCalc(Property property){
        Double result = 0.0;
        for (Room room : property.getRoomList()){
            result += totalSquareMeterRoom(room);
        }
        return result;
    }

    public Double totalSquareMeterRoom(Room room){
        return room.getLenght() * room.getWidth();
    }
}
