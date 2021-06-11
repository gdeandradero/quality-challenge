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
public class Room {
    @NotBlank(message = "Property name of the room cannot be empty")
    @Size(max = 30, message = "The maximum size of the room name is 30")
    @Pattern(regexp = "^[A-z ]+$", message = "The first letter of the room name must be capitalized," +
            "and only letters are allowed")
    private String name;
    @NotNull(message = "The field width of the room cannot be empty")
    @Max(value = 25, message = "The maximum width of the room is 25 meters")
    private Double width;
    @NotNull(message = "The field lenght of the room cannot be empty")
    @Max(value = 33, message = "The maximum width of the room is 33 meters")
    private Double lenght;
}
