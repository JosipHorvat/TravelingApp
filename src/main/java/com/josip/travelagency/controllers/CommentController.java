package com.josip.travelagency.controllers;

import com.josip.travelagency.model.Comment;
import com.josip.travelagency.model.Tour;
import com.josip.travelagency.service.CommentService;
import com.josip.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private TourService tourService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/addComment")
    public String showCommentForm(Model model) {
        List<Tour> tours = tourService.getAll();
        model.addAttribute("tours", tours);
        model.addAttribute("comment", new Comment());
        return "form-comment";
    }

    @PostMapping("/processFormComment")
    public String addCommentData(@ModelAttribute Comment comment) {
        commentService.save(comment);
        return "home";
    }
}
