package com.josip.travelagency.controllers;

import com.josip.travelagency.model.TAUser;
import com.josip.travelagency.service.TAUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private TAUserService userService;

    @RequestMapping("login")
    public String showLoginPage(){
        return "login";
    }

    @RequestMapping("forbidden")
    public String showForbiddenError(){
        return "403";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("user", new TAUser());
        return "form-signup";
    }

    @PostMapping("processSignup")
    public String processSignup(@ModelAttribute TAUser user, RedirectAttributes redirectAttributes) {
        boolean errors = false;

        if(!user.getPassword().equals(user.getConfirmedPassword())) {
            redirectAttributes.addAttribute("differentPasswords", "Passwords are different");
            errors = true;
        }
        if(userService.loginExists(user.getLogin())) {
            redirectAttributes.addAttribute("loginExists", "Login already exists in the database");
            errors = true;
        }

        if(errors) {
            return "redirect:/signup";
        }

        userService.createNewAccount(user);
        return "login";
    }

    @PostMapping("/checkUserAccount")
    public String login(){
        return "/";
    }
}
