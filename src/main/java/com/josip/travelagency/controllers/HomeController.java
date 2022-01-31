package com.josip.travelagency.controllers;

import com.josip.travelagency.model.Tour;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    //instead of database
    private List<Tour> tours = new ArrayList<Tour>();

    //http://localhost:8080/
    @RequestMapping("/")
    public String getHome(){
        return "home";
    }

    @GetMapping("/addTour")
    public String showForm(Model model) {
        model.addAttribute("tour", new Tour());
        return "form";
    }

    @PostMapping("processForm")
    public String showTourData(@ModelAttribute Tour tour) {
        tours.add(tour);
        return "redirect:showOffer";
    }

    @GetMapping("/showOffer")
    public String getTours(Model model) {
        model.addAttribute("tours", tours);
        return "tours";
    }
}
