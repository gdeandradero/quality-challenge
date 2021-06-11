package com.meli.qualitychallenge.service;

import com.meli.qualitychallenge.models.Property;
import com.meli.qualitychallenge.models.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface PropertyService {
    ResponseEntity<Double> totalSquareMeterProperty(Property property);
    ResponseEntity<?> totalPriceProperty(Property property);
    ResponseEntity<Room> biggestRoom(Property property);
    ResponseEntity<Map<String, Double>> totalSquareMeterRooms(Property property);
}
