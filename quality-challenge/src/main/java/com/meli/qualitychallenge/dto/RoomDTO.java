package com.meli.qualitychallenge.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    @NotBlank(message = "Field name of the room cannot be empty")
    @Size(max = 30, message = "The maximum size of the field name of the room is 30")
    @Pattern(regexp = "^[A-Z][A-z ]*$", message = "The first letter of the room name must be capitalized," +
            " and only letters are allowed")
    private String name;
    @NotNull(message = "The field width of the room cannot be empty")
    @Max(value = 25, message = "The maximum width of the room is 25 meters")
    private Double width;
    @NotNull(message = "The field length of the room cannot be empty")
    @Max(value = 33, message = "The maximum width of the room is 33 meters")
    private Double length;
}
