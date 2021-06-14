package com.meli.qualitychallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/*
 * US 0004 - squareMeterRooms
 */
public class SquareMeterRoomsDTO {
    List<SquareMeterRoomDTO> squareMeterRoomDTOList;
}
