package com.meli.qualitychallenge;

import com.meli.qualitychallenge.exceptions.DistrictError;
import com.meli.qualitychallenge.models.Property;
import com.meli.qualitychallenge.models.Room;
import com.meli.qualitychallenge.service.PropertyServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QualityChallengeApplicationTests {

	private static Property property;
	PropertyServiceImpl service = new PropertyServiceImpl();

	@BeforeAll
	public static void arrange(){
		List<Room> roomList = new ArrayList<>();
		roomList.add(new Room("Room", 20.0, 20.0));
		roomList.add(new Room("Living Room", 5.0, 5.0));
		roomList.add(new Room("Bathroom", 1.0, 1.0));
		property = new Property("House One", "Yellow District", roomList);
	}

	@Test
	void shouldReturnTotalSquareMeterProperty(){
		// arrange
		ResponseEntity<Double> expected = new ResponseEntity<>(426.0, HttpStatus.OK);

		// act
		ResponseEntity<Double> result = service.totalSquareMeterProperty(property);

		// assert
		assertEquals(expected, result);
	}

	@Test
	void shouldReturnPriceDistrict(){
		// arrange
		Double expected = 100.0;

		// act
		Double districtPrice = service.getDistrictPrice(property);

		// assert
		assertEquals(expected, districtPrice);
	}

	@Test
	void shouldReturnException(){
		// arrange
		Property p = property;
		p.setDistrict("Any District");

		// act
		try {
			service.totalPriceProperty(p);
			fail("this test should return a exception");
		// equals
		} catch (DistrictError ex){
			assertEquals("District not found", ex.getMessage());
		}
	}

	@Test
	void shouldReturnBiggerRoom(){
		// arrange
		ResponseEntity<Room> expected = new ResponseEntity<>(property.getRoomList().get(0), HttpStatus.OK);

		// act
		ResponseEntity<Room> actual = service.biggestRoom(property);

		// assert
		assertEquals(expected, actual);
	}

	@Test
	void shouldReturnTotalSquareMeterRoom(){
		// arrange
		Double expected = 400.0;

		// act
		Double result = service.totalSquareMeterRoom(property.getRoomList().get(0));

		// assert
		assertEquals(expected, result);
	}

}
