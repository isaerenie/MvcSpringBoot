package com.example.controllers;

import com.example.entities.Admin;
import com.example.services.AdminServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    final AdminServices adminServices;

    public LoginController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @GetMapping("/")
    public String login(Model model){
        model.addAttribute("data","isa Eren");
        return "login";
    }
    @PostMapping("/login")
    public String login(Admin admin){
        return adminServices.login(admin);
    }
}
