package com.josip.travelagency.controllers;

import com.josip.travelagency.model.Tour;
import com.josip.travelagency.repository.TourRepository;
import com.josip.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/addTour")
    public String showForm(Model model) {
        model.addAttribute("tour", new Tour());
        return "tour/tour-form";
    }

    @PostMapping("/processForm")
    public String showTourData(@Valid @ModelAttribute Tour tour, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "tour/tour-form";
        }
        tourService.saveOrUpdate(tour);
        return "redirect:showOffer";
    }

    @GetMapping(path = {"/showOffer", "/search"})
    public String getTours(Model model, Tour tour, String keyword) {

        if(keyword!=null) {
            List<Tour> tours = tourService.findByKeyword(keyword);
            model.addAttribute("tours", tours);
        }else {
            List<Tour> tours = tourService.getAll();
            model.addAttribute("tours", tours);
            return "tour/tours";
    }
        return "tour/tours";
    }

    @GetMapping("/deleteTour/{id}")
    public String deleteTour(@PathVariable long id) {
        Tour tour = tourService.getById(id);
        if(tour != null) {
            tourService.delete(id);
        }
        return "redirect:/showOffer";
    }

    @GetMapping("/editTour/{id}")
    public String editTour(@PathVariable long id, Model model) {
        Tour tour = tourService.getById(id);
        if(tour != null) {
            model.addAttribute("tour", tour);
            return "tour/tour-form";
        }
        return "redirect:/showOffer";
    }

    @GetMapping("/addUserToTour/{id}")
    public String addUserToTour(@PathVariable int id, Principal principal) {
        tourService.addUserToTour(id, principal.getName());
        return "redirect:/showOffer";
    }

}
