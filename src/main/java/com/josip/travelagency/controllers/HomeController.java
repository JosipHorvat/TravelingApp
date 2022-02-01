package com.josip.travelagency.controllers;

import com.josip.travelagency.model.Tour;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    //instead of database
    private List<Tour> tours = new ArrayList<Tour>();

    //http://localhost:8080/
    @RequestMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/addTour")
    public String showForm(Model model) {
        model.addAttribute("tour", new Tour());
        return "form";
    }

    @PostMapping("processForm")
    public String showTourData(@Valid @ModelAttribute Tour tour, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }
        tours.add(tour);
        return "redirect:showOffer";
    }

    @GetMapping("/showOffer")
    public String getTours(Model model) {
        model.addAttribute("tours", tours);
        return "tours";
    }
}
