package com.josip.travelagency.externalapi.model.openweathermap;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Wind {

    private double speed;
    private double deg;
    private double gust;
}
