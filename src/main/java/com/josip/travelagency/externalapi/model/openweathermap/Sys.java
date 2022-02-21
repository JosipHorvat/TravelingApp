package com.josip.travelagency.externalapi.model.openweathermap;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Sys {

    private long type;
    private long id;
    private String message;
    private String country;
    private long sunrise;
    private long sunset;
}
