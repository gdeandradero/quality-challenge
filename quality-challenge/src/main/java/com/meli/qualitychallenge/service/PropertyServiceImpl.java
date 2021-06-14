package com.meli.qualitychallenge.service;

import com.meli.qualitychallenge.dao.DistrictDAO;
import com.meli.qualitychallenge.dto.PropertyDTO;
import com.meli.qualitychallenge.dto.SquareMeterRoomDTO;
import com.meli.qualitychallenge.dto.SquareMeterRoomsDTO;
import com.meli.qualitychallenge.exceptions.DistrictException;
import com.meli.qualitychallenge.models.RoomDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class PropertyServiceImpl implements PropertyService {

    /*
     * US 0001 - totalSquareMeterProperty
     */
    @Override
    public ResponseEntity<Double> totalSquareMeterProperty(PropertyDTO propertyDTO) {
        boolean haveNoRoomsOrAreNull = propertyDTO == null || propertyDTO.getRoomList() == null;
        if (haveNoRoomsOrAreNull) {
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
        boolean haveNoRoomsOrAreNull = propertyDTO == null || propertyDTO.getRoomList() == null;
        if (haveNoRoomsOrAreNull) {
            return new ResponseEntity<>(new RoomDTO(), HttpStatus.BAD_REQUEST);
        }
        RoomDTO biggestRoomAttribute = new RoomDTO();
        biggestRoomAttribute.setWidth(0.0);
        biggestRoomAttribute.setLength(0.0);
        for (RoomDTO roomDTO : propertyDTO.getRoomList()) {
            if (squareMeterRoom(roomDTO) > squareMeterRoom(biggestRoomAttribute)) {
                biggestRoomAttribute = roomDTO;
            }
        }
        return new ResponseEntity<>(biggestRoomAttribute, HttpStatus.OK);
    }

    /*
     * US 0004 - totalSquareMeterRooms
     */
    @Override
    public ResponseEntity<SquareMeterRoomsDTO> squareMeterRooms(PropertyDTO propertyDTO) {
        boolean haveNoRoomsOrAreNull = propertyDTO == null || propertyDTO.getRoomList() == null;
        if (haveNoRoomsOrAreNull) {
            return new ResponseEntity<>(new SquareMeterRoomsDTO(), HttpStatus.BAD_REQUEST);
        }
        SquareMeterRoomsDTO dto = new SquareMeterRoomsDTO(new ArrayList<>());
        propertyDTO.getRoomList().forEach(roomDTO -> {
            dto.getSquareMeterRoomDTOList().add(new SquareMeterRoomDTO(roomDTO.getName(), squareMeterRoom(roomDTO)));
        });
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    private Double totalSquareMeterPropertyCalc(PropertyDTO propertyDTO) {
        return propertyDTO.getRoomList().stream()
                .mapToDouble(roomDTO -> squareMeterRoom(roomDTO))
                .sum();
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

    public Double squareMeterRoom(RoomDTO roomDTO) {
        return roomDTO.getLength() * roomDTO.getWidth();
    }
}
