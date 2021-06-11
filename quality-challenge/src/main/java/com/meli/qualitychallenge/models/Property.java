package com.meli.qualitychallenge.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @NotBlank(message = "Property name cannot be empty")
    @Size(max = 30, message = "The maximum size of the property name is 30")
    @Pattern(regexp = "^[A-z ]+$", message = "The first letter of the property name must be capitalized," +
            "and only letters are allowed")
    private String name;
    @NotBlank(message = "Property district cannot be empty")
    @Size(max = 45, message = "The maximum size of the field district is 45")
    private String district;
    @Valid List<Room> roomList;
}
