package fajnelokalne.demo.controller.app;

import fajnelokalne.demo.entity.City;
import fajnelokalne.demo.entity.Company;
import fajnelokalne.demo.service.CompanyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/city")
public class AppCityController {

    @Autowired
    CompanyManager companyManager;

    @GetMapping("")
    String list() {
        return "city/list";
    }

    @GetMapping("/{id}")
    String view(
            @PathVariable("id") City entity,
            Model model,
            Pageable pageable
    ) {
        model.addAttribute("object", entity);
        Page<Company> objectsList = companyManager.findAllByCity(entity, pageable);
        model.addAttribute("objectsList", objectsList);
        return "city/view";
    }
}
