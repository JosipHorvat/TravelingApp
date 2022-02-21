package com.josip.travelagency.externalapi.model.openweathermap;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Rain {

    @JsonAlias("1h")
    private double oneH;
    @JsonAlias("3h")
    private double threeH;
}
