package com.meli.qualitychallenge.exceptions;

public class DistrictError extends NullPointerException {
    public DistrictError(){
        super("District not found");
    }
}
