package com.meli.qualitychallenge.service;

import com.meli.qualitychallenge.dto.PropertyDTO;
import com.meli.qualitychallenge.dto.SquareMeterRoomsDTO;
import com.meli.qualitychallenge.models.RoomDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PropertyService {
    ResponseEntity<Double> totalSquareMeterProperty(PropertyDTO propertyDTO);

    ResponseEntity<Double> totalPriceProperty(PropertyDTO propertyDTO);

    ResponseEntity<RoomDTO> biggestRoom(PropertyDTO propertyDTO);

    ResponseEntity<SquareMeterRoomsDTO> squareMeterRooms(PropertyDTO property);
}
