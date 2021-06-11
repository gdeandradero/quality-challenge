package com.meli.qualitychallenge.service;

import com.meli.qualitychallenge.dao.DistrictDAO;
import com.meli.qualitychallenge.dto.PropertyDTO;
import com.meli.qualitychallenge.exceptions.DistrictException;
import com.meli.qualitychallenge.models.RoomDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class PropertyServiceImpl implements PropertyService {

    /*
     * US 0001 - totalSquareMeterProperty
     */
    @Override
    public ResponseEntity<Double> totalSquareMeterProperty(PropertyDTO propertyDTO) {
        if (propertyDTO == null || propertyDTO.getRoomList() == null) {
            return new ResponseEntity<>(0.0, HttpStatus.BAD_REQUEST);
        }
        Double result = totalSquareMeterPropertyCalc(propertyDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /*
     * US 0002 - totalPriceProperty
     */
    @Override
    public ResponseEntity<Double> totalPriceProperty(PropertyDTO propertyDTO) {
        boolean haveNoRoomsOrAreNull = propertyDTO == null || propertyDTO.getRoomList() == null;
        if (haveNoRoomsOrAreNull) {
            return new ResponseEntity<>(0.0, HttpStatus.BAD_REQUEST);
        }
        Double priceDistrict = getDistrictPrice(propertyDTO);
        Double sizeProperty = totalSquareMeterPropertyCalc(propertyDTO);
        Double totalPrice = sizeProperty * priceDistrict;
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    /*
     * US 0003 - biggestRoom
     */
    @Override
    public ResponseEntity<RoomDTO> biggestRoom(PropertyDTO propertyDTO) {
        if (propertyDTO == null || propertyDTO.getRoomList() == null) {
            return new ResponseEntity<>(new RoomDTO(), HttpStatus.BAD_REQUEST);
        }
        RoomDTO biggestRoomAttribute = new RoomDTO();
        biggestRoomAttribute.setWidth(0.0);
        biggestRoomAttribute.setLenght(0.0);
        for (RoomDTO roomDTO : propertyDTO.getRoomList()) {
            if (totalSquareMeterRoom(roomDTO) > totalSquareMeterRoom(biggestRoomAttribute)) {
                biggestRoomAttribute = roomDTO;
            }
        }
        return new ResponseEntity<>(biggestRoomAttribute, HttpStatus.OK);
    }

    /*
     * US 0004 - totalSquareMeterRooms
     */
    @Override
    public ResponseEntity<Map<String, Double>> totalSquareMeterRooms(PropertyDTO propertyDTO) {
        if (propertyDTO == null || propertyDTO.getRoomList() == null) {
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.BAD_REQUEST);
        }
        Map<String, Double> resultMap = new HashMap<>();
        for (RoomDTO roomDTO : propertyDTO.getRoomList()) {
            resultMap.put(roomDTO.getName(), totalSquareMeterRoom(roomDTO));
        }
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    private Double totalSquareMeterPropertyCalc(PropertyDTO propertyDTO) {
        Double result = 0.0;
        for (RoomDTO roomDTO : propertyDTO.getRoomList()) {
            result += totalSquareMeterRoom(roomDTO);
        }
        return result;
    }

    public Double getDistrictPrice(PropertyDTO propertyDTO) {
        DistrictDAO.fillMap();
        if (!DistrictDAO
                .districtMap
                .containsKey(propertyDTO.getDistrict().toUpperCase())) {
            throw new DistrictException();
        }
        return DistrictDAO
                .districtMap
                .get(propertyDTO.getDistrict().toUpperCase());
    }

    public Double totalSquareMeterRoom(RoomDTO roomDTO) {
        return roomDTO.getLenght() * roomDTO.getWidth();
    }
}
