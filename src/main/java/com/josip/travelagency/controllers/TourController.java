package com.josip.travelagency.controllers;

import com.josip.travelagency.model.Tour;
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
import java.util.List;

@Controller
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/addTour")
    public String showForm(Model model) {
        model.addAttribute("tour", new Tour());
        System.out.println("ovo mi se pozvalo");
        return "form";
    }

    @PostMapping("/processForm")
    public String showTourData(@Valid @ModelAttribute Tour tour, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "form";
        }
        tourService.saveOrUpdate(tour);
        return "redirect:showOffer";
    }

    @GetMapping("/showOffer")
    public String getTours(Model model) {
        List<Tour> tours = tourService.getAll();
        model.addAttribute("tours", tours);
        return "tours";
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
            return "form";
        }
        return "redirect:/showOffer";
    }

    @GetMapping("/addUserToTour/{id}/{userId}")
    public String addUserToTour(@PathVariable long id, @PathVariable long userId){
        tourService.addUserToTour(id, userId);
        return "redirect:/showOffer";
    }

    /*
    Users are hardcoded for now. To make app work you need to insert 2 users in database using query:

    insert into traveling_agency.user(id, login) values(1, 'Josip');
    insert into traveling_agency.user(id, login) values(2, 'Mile');

    In html page there are 2 "user" buttons. Button 1 represents user 1, and button 2 user 2;
     */
}
