package com.meli.qualitychallenge.controller;

import com.meli.qualitychallenge.models.Property;
import com.meli.qualitychallenge.models.Room;
import com.meli.qualitychallenge.service.PropertyService;
import com.meli.qualitychallenge.service.PropertyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/property")
public class PropertyController {

    PropertyService propertyService = new PropertyServiceImpl();

    /*
     * US 0001 - totalSquareMeterProperty
     */
    @GetMapping("/total-square-meter-property")
    public ResponseEntity<Double> totalSquareMeterProperty(@Valid @RequestBody Property property){
        return propertyService.totalSquareMeterProperty(property);
    }

    /*
     * US 0002 - totalPriceProperty
     */
    @GetMapping("/total-price-property")
    public ResponseEntity<?> totalPriceProperty(@Valid @RequestBody Property property){
        return propertyService.totalPriceProperty(property);
    }

    /*
     * US 0003 - biggestRoom
     */
    @GetMapping("/biggest-room")
    public ResponseEntity<Room> biggestRoom(@Valid @RequestBody Property property){
        return propertyService.biggestRoom(property);
    }

    /*
     * US 0004 - totalSquareMeterRooms
     */
    @GetMapping("/total-square-meter-rooms")
    public ResponseEntity<Map<String, Double>> totalSquareMeterRooms(@Valid @RequestBody Property property){
        return propertyService.totalSquareMeterRooms(property);
    }


}
