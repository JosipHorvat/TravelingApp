package com.josip.travelagency.controllers;

import com.josip.travelagency.model.Tour;
import com.josip.travelagency.model.TourDetails;
import com.josip.travelagency.service.TourDetailsService;
import com.josip.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TourDetailsController {

    @Autowired
    private TourService tourService;

    @Autowired
    private TourDetailsService tourDetailsService;


    @GetMapping("/showTourDetails/{tourId}")
    public String showTourDetails(@PathVariable int tourId, Model model ) {
        Tour tour = tourService.getById(tourId);
        if(tour != null) {
            model.addAttribute("tour", tour);
            return "tour-details";
        }
        return "redirect:/showOffer";
    }

    @GetMapping("/editTourDetails/{tourId}")
    public String editTourDetails(@PathVariable long tourId, Model model) {
        System.out.println("U tour details kontoleru sam");
        Tour tour = tourService.getById(tourId);
        if(tour != null) {
            model.addAttribute("tourDetails", tour.getTourDetails());
            return "form-tour-details";
        }
        return "redirect:/showOffer";
    }

    @PostMapping("/processFormTourDetails")
    public String processTourDetailsData(@ModelAttribute TourDetails tourDetails) {
        tourDetailsService.saveOrUpdate(tourDetails);
        return "redirect:/showOffer";
    }
}
