package fajnelokalne.demo.controller;

import fajnelokalne.demo.formdata.RegistrationFormData;
import fajnelokalne.demo.service.SecurityService;
import fajnelokalne.demo.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class SecurityController {
    @Autowired
    private UserManager userService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("formData", new RegistrationFormData());

        return "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("formData") RegistrationFormData formData, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(formData);

        securityService.autologin(formData.getUsername(), formData.getPassword());

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "login";
    }
}