package com.josip.travelagency.externalapi.externalapicontroller;

import com.josip.travelagency.externalapi.externalapiservice.OpenWeatherMapService;
import com.josip.travelagency.externalapi.model.openweathermap.OpenWeatherMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OpenWeatherMapController {

    @Autowired
    private OpenWeatherMapService openWeatherMapService;

    @RequestMapping("/getWeather")
    public String getWeather(HttpServletRequest request, Model model) {
        OpenWeatherMap openWeatherMap = openWeatherMapService.getData(request);
        model.addAttribute("openWeatherMap", openWeatherMap);
        return "externalapi/weather";
    }
}
