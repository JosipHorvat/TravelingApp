package com.josip.travelagency.controllers;

import com.josip.travelagency.model.Tour;
import com.josip.travelagency.service.TourDetailsService;
import com.josip.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class TourDetailsController {

    @Autowired
    private TourService tourService;

    @Autowired
    private TourDetailsService tourDetailsService;

    // TODO: 03/02/2022 zavrsit kontroler, HTML: forma za tour details, i show tour-details 
}
