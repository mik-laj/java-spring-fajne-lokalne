package fajnelokalne.demo.controller.app;

import fajnelokalne.demo.repository.CityRepository;
import fajnelokalne.demo.repository.ProductRepository;
import fajnelokalne.demo.service.CityManager;
import fajnelokalne.demo.service.ProductManager;
import fajnelokalne.demo.service.TagManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    ProductManager productManager;

    @Autowired
    CityManager cityManager;

    @Autowired
    TagManager tagManager;

    @GetMapping("/")
    public String homePage(Model model) {

        model.addAttribute("products", productManager.findAll(new PageRequest(0, 9)));
        model.addAttribute("cities", cityManager.findAll(new PageRequest(0, 20)));
        model.addAttribute("tags", tagManager.findAll());

        return "home";
    }
}
