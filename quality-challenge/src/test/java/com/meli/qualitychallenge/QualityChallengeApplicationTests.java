package com.meli.qualitychallenge;

import com.meli.qualitychallenge.dto.PropertyDTO;
import com.meli.qualitychallenge.exceptions.DistrictException;
import com.meli.qualitychallenge.models.RoomDTO;
import com.meli.qualitychallenge.service.PropertyServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QualityChallengeApplicationTests {

    private static PropertyDTO propertyDTO;
    PropertyServiceImpl service = new PropertyServiceImpl();

    @BeforeAll
    public static void arrange() {
        List<RoomDTO> roomList = new ArrayList<>();
        roomList.add(new RoomDTO("Room", 20.0, 20.0));
        roomList.add(new RoomDTO("Living Room", 5.0, 5.0));
        roomList.add(new RoomDTO("Bathroom", 1.0, 1.0));
        propertyDTO = new PropertyDTO("House One", "Yellow District", roomList);
    }

    @Test
    void shouldReturnTotalSquareMeterProperty() {
        // arrange
        ResponseEntity<Double> expected = new ResponseEntity<>(426.0, HttpStatus.OK);

        // act
        ResponseEntity<Double> result = service.totalSquareMeterProperty(propertyDTO);

        // assert
        assertEquals(expected, result);
    }

    @Test
    void shouldReturnPriceDistrict() {
        // arrange
        Double expected = 100.0;

        // act
        Double districtPrice = service.getDistrictPrice(propertyDTO);

        // assert
        assertEquals(expected, districtPrice);
    }

    @Test
    void shouldReturnException() {
        // arrange
        PropertyDTO p = propertyDTO;
        p.setDistrict("Any District");

        // act e assert
        assertThrows(DistrictException.class, () -> {
            service.totalPriceProperty(propertyDTO);
        });
    }

    @Test
    void shouldReturnBiggerRoom() {
        // arrange
        ResponseEntity<RoomDTO> expected = new ResponseEntity<>(propertyDTO.getRoomList().get(0), HttpStatus.OK);

        // act
        ResponseEntity<RoomDTO> actual = service.biggestRoom(propertyDTO);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnTotalSquareMeterRoom() {
        // arrange
        Double expected = 400.0;

        // act
        Double result = service.totalSquareMeterRoom(propertyDTO.getRoomList().get(0));

        // assert
        assertEquals(expected, result);
    }

}
