package com.meli.qualitychallenge.dto;

import com.meli.qualitychallenge.models.RoomDTO;
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
public class PropertyDTO {
    @NotBlank(message = "Field name of the property cannot be empty")
    @Size(max = 30, message = "The maximum size of the field name of the property is 30")
    @Pattern(regexp = "^[A-Z][A-z ]*$", message = "The first letter of the property name must be capitalized," +
            " and only letters are allowed")
    private String name;
    @NotBlank(message = "Field district of the property cannot be empty")
    @Size(max = 45, message = "The maximum size of the field district of the property is 45")
    private String district;
    @Valid List<RoomDTO> roomList;
}
