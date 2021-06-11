package com.meli.qualitychallenge.controller;

import com.meli.qualitychallenge.dto.PropertyDTO;
import com.meli.qualitychallenge.models.RoomDTO;
import com.meli.qualitychallenge.service.PropertyService;
import com.meli.qualitychallenge.service.PropertyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/property")
public class PropertyController {

    PropertyService propertyService = new PropertyServiceImpl();

    /*
     * US 0001 - totalSquareMeterProperty
     */
    @PostMapping("/total-square-meter-property")
    public ResponseEntity<Double> totalSquareMeterProperty(@Valid @RequestBody PropertyDTO propertyDTO) {
        return propertyService.totalSquareMeterProperty(propertyDTO);
    }

    /*
     * US 0002 - totalPriceProperty
     */
    @PostMapping("/total-price-property")
    public ResponseEntity<?> totalPriceProperty(@Valid @RequestBody PropertyDTO propertyDTO) {
        return propertyService.totalPriceProperty(propertyDTO);
    }

    /*
     * US 0003 - biggestRoom
     */
    @PostMapping("/biggest-room")
    public ResponseEntity<RoomDTO> biggestRoom(@Valid @RequestBody PropertyDTO propertyDTO) {
        return propertyService.biggestRoom(propertyDTO);
    }

    /*
     * US 0004 - totalSquareMeterRooms
     */
    @PostMapping("/total-square-meter-rooms")
    public ResponseEntity<Map<String, Double>> totalSquareMeterRooms(@Valid @RequestBody PropertyDTO propertyDTO) {
        return propertyService.totalSquareMeterRooms(propertyDTO);
    }

}
