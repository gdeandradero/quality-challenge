package com.meli.qualitychallenge.controller;

import com.meli.qualitychallenge.dto.PropertyDTO;
import com.meli.qualitychallenge.models.RoomDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropertyControllerTest {

    private static PropertyDTO propertyDTO;
    PropertyController controller = new PropertyController();

    @BeforeAll
    public static void init() {
        List<RoomDTO> roomList = new ArrayList<>();
        roomList.add(new RoomDTO("Room", 20.0, 20.0));
        roomList.add(new RoomDTO("Living Room", 5.0, 5.0));
        roomList.add(new RoomDTO("Bathroom", 1.0, 1.0));
        propertyDTO = new PropertyDTO("House One", "Yellow District", roomList);
    }

    @LocalServerPort
    private int port;

    @Test
    void totalSquareMeterProperty() {
        Response response = given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(propertyDTO)
                .when()
                .post("/property/total-square-meter-property")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(426.0, Double.parseDouble(response.asString()));
    }

    @Test
    void totalPriceProperty() {
        Response response = given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(propertyDTO)
                .when()
                .post("/property/total-price-property")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(42600.0, Double.parseDouble(response.asString()));
    }

    @Test
    void biggestRoom() {
        Response response = given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(propertyDTO)
                .when()
                .post("/property/biggest-room")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Room", response.jsonPath().getString("name"));
        Assertions.assertEquals(20.0, response.jsonPath().getDouble("width"));
        Assertions.assertEquals(20.0, response.jsonPath().getDouble("length"));
    }

    @Test
    void squareMeterRooms() {
        Response response = given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(propertyDTO)
                .when()
                .post("/property/square-meter-rooms")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Room",
                response.jsonPath().getString("squareMeterRoomDTOList[0].name"));
        Assertions.assertEquals("400.0",
                response.jsonPath().getString("squareMeterRoomDTOList[0].squareMeter"));
        Assertions.assertEquals("Living Room",
                response.jsonPath().getString("squareMeterRoomDTOList[1].name"));
        Assertions.assertEquals("25.0",
                response.jsonPath().getString("squareMeterRoomDTOList[1].squareMeter"));
        Assertions.assertEquals("Bathroom",
                response.jsonPath().getString("squareMeterRoomDTOList[2].name"));
        Assertions.assertEquals("1.0",
                response.jsonPath().getString("squareMeterRoomDTOList[2].squareMeter"));
    }

}