package com.josip.travelagency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    //http://localhost:8080/
    @RequestMapping("/")
    public String getHome() {
        return "home";
    }

    // TODO: 02/02/2022 tours.html and form: update delete, add description of tour.code pattern, change all inclusive data to yes or no
// TODO: 02/02/2022  model TourDetails-> one to one with tour, service repo controller. HTML : form tour-details and tourdetails
}
