package com.meli.qualitychallenge.service;

import com.meli.qualitychallenge.dto.PropertyDTO;
import com.meli.qualitychallenge.models.RoomDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface PropertyService {
    ResponseEntity<Double> totalSquareMeterProperty(PropertyDTO propertyDTO);

    ResponseEntity<?> totalPriceProperty(PropertyDTO propertyDTO);

    ResponseEntity<RoomDTO> biggestRoom(PropertyDTO propertyDTO);

    ResponseEntity<Map<String, Double>> totalSquareMeterRooms(PropertyDTO property);
}
