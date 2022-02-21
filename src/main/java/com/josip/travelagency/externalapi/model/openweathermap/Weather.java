package com.josip.travelagency.externalapi.model.openweathermap;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Weather {

    private long id;
    private String main;
    private String description;
    private String icon;
}
