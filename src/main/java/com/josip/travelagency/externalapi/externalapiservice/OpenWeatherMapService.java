package com.josip.travelagency.externalapi.externalapiservice;

import com.josip.travelagency.externalapi.model.openweathermap.OpenWeatherMap;

import javax.servlet.http.HttpServletRequest;

public interface OpenWeatherMapService {

    public OpenWeatherMap getData(HttpServletRequest request);
}
